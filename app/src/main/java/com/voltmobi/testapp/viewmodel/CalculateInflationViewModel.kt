package com.voltmobi.testapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.voltmobi.testapp.arch.ViewModel
import com.voltmobi.testapp.data.model.YearsWithMonths
import com.voltmobi.testapp.data.repository.InflationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalculateInflationViewModel (
    val context: Context,
    val inflationRepository: InflationRepository
) : ViewModel() {

    var dateSelectData = MutableLiveData<DateSelectData>()
    var inflactionData = MutableLiveData<InflactionData>()

    var inflations: List<YearsWithMonths> = ArrayList()

    var startYear = 0
    var endYear = 0
    var startMonth = 0
    var endMonth = 0

    override fun onUiAttach() {
        super.onUiAttach()
        viewModelScope.launch(Dispatchers.Default) {
            inflations = inflationRepository.getYearsWithMonths()
            if (inflations.isNotEmpty()) {
                val minYear = inflations.minBy { yearsWithMonths -> yearsWithMonths.year.name }?.year?.name ?: 1999
                val maxYear = inflations.maxBy { yearsWithMonths -> yearsWithMonths.year.name }?.year?.name ?: 2001

                withContext(Dispatchers.Main) {
                    dateSelectData.value = DateSelectData(
                        minYear = minYear,
                        maxYear = maxYear
                    )
                }
            }
        }
    }

    fun calculateInflationRange() {
        val filterYears = inflations.filter { yearsWithMonths -> yearsWithMonths.year.name in startYear..endYear }.toMutableList()

        if (startYear != endYear) {
            val startMonths = filterYears.find { yearsWithMonths -> yearsWithMonths.year.name == startYear }
                ?.months?.filterIndexed { index, _ -> index >= startMonth }
            val countStartMonth = startMonths?.count() ?: 0
            val sumStartYearInflate = startMonths?.sumByDouble { month -> month.value.toDouble() }?.toFloat() ?: 0F

            val endMonths = filterYears.find { yearsWithMonths -> yearsWithMonths.year.name == endYear }
                ?.months?.filterIndexed { index, _ -> index >= endMonth }
            val countEndMonth = endMonths?.count() ?: 0
            val sumEndYearInflate = endMonths
                ?.sumByDouble { month -> month.value.toDouble() }?.toFloat() ?: 0F


            val removeFilter = filterYears.filter { yearsWithMonths -> yearsWithMonths.year.name == startYear || yearsWithMonths.year.name == endYear }
            filterYears.removeAll(removeFilter)

            val countLastMonths = filterYears.count()
            val lastSum = filterYears.sumByDouble { yearsWithMonths -> yearsWithMonths.months.sumByDouble { month -> month.value.toDouble() } }.toFloat()

            val countMonth = countStartMonth + countEndMonth + countLastMonths
            val totalSum = sumStartYearInflate + sumEndYearInflate + lastSum
            inflactionData.value = InflactionData(
                countMonth = countMonth,
                totalSum = totalSum
            )

        } else {
            val filteredMonth = filterYears.find { yearsWithMonths -> yearsWithMonths.year.name == startYear }
                ?.months?.filterIndexed { index, _ -> index in startMonth..endMonth }
            val countMonth = filteredMonth?.count() ?: 0
            val totalSum = filteredMonth?.sumByDouble { month -> month.value.toDouble() }?.toFloat() ?: 0F

            inflactionData.value = InflactionData(
                countMonth = countMonth,
                totalSum = totalSum
            )
        }

    }

    data class DateSelectData(
        val minYear: Int,
        val maxYear: Int
    )

    data class InflactionData(
        val countMonth: Int,
        val totalSum: Float
    )
}
