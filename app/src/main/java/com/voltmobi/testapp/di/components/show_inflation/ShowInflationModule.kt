package com.voltmobi.testapp.di.components.show_inflation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voltmobi.testapp.data.manager.ParserCSVManager
import com.voltmobi.testapp.data.manager.inflation.InflationManager
import com.voltmobi.testapp.data.repository.InflationRepository
import com.voltmobi.testapp.di.qualifiers.FragmentScope
import com.voltmobi.testapp.viewmodel.ShowInflationViewModel
import dagger.Module
import dagger.Provides

@Module
class ShowInflationModule(
    private val context: Context
) {
    @Suppress("UNCHECKED_CAST")
    @FragmentScope
    @Provides
    fun getFactoryMain(
        inflationRepository: InflationRepository,
        inflationManager: InflationManager
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ShowInflationViewModel(context, inflationRepository, inflationManager, ParserCSVManager(context, inflationRepository)) as T
            }
        }
    }
}