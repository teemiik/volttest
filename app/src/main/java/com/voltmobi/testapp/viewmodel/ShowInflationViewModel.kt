package com.voltmobi.testapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.voltmobi.testapp.R
import com.voltmobi.testapp.arch.ViewModel
import com.voltmobi.testapp.data.manager.ParserCSVManager
import com.voltmobi.testapp.data.manager.inflation.InflationManager
import com.voltmobi.testapp.data.model.YearsWithMonths
import com.voltmobi.testapp.data.repository.InflationRepository
import com.voltmobi.testapp.tools.error.ApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowInflationViewModel(
    private val context: Context,
    private val inflationRepository: InflationRepository,
    private val inflationManager: InflationManager,
    private val parserCSVManager: ParserCSVManager
) : ViewModel() {

    var data = MutableLiveData<YearsAndMonthData>()
    var errorData = MutableLiveData<ErrorData>()

    override fun onUiAttach() {
        super.onUiAttach()
        viewModelScope.launch(Dispatchers.Default) {
            var countYear = inflationRepository.getCount()
            try {
                val inputStream = inflationManager.getInflation()
                    ?: throw ApiError(context.getString(R.string.http_error_unknown_error))

                deleteTables()
                parserCSVManager.parse(inputStream)
                countYear = inflationRepository.getCount()
            } catch (e: ApiError) {
                if (countYear == 0) {
                    withContext(Dispatchers.Main) {
                        errorData.value = ErrorData(
                            messegeError = e.message
                                ?: context.getString(R.string.http_error_unknown_error)
                        )
                    }
                }
            } finally {
                initInflation(countYear)
            }

        }
    }

    private fun deleteTables() {
        inflationRepository.deleteYear()
        inflationRepository.deleteMonth()
    }

    private suspend fun initInflation(countYear: Int) {
        if (countYear != 0) {
            val inflations = inflationRepository.getYearsWithMonths()
            if (inflations.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    data.value = YearsAndMonthData(
                        yearsAndMonthData = inflations
                    )
                }
            }
        }
    }

    data class YearsAndMonthData (
        val yearsAndMonthData: List<YearsWithMonths>
    )

    data class ErrorData(
        val messegeError: String = ""
    )

}