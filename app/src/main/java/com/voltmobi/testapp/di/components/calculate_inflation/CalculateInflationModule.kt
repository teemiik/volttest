package com.voltmobi.testapp.di.components.calculate_inflation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voltmobi.testapp.data.repository.InflationRepository
import com.voltmobi.testapp.di.qualifiers.FragmentScope
import com.voltmobi.testapp.viewmodel.CalculateInflationViewModel
import dagger.Module
import dagger.Provides

@Module
class CalculateInflationModule(
    private val context: Context
) {
    @Suppress("UNCHECKED_CAST")
    @FragmentScope
    @Provides
    fun getFactoryMain(
        inflationRepository: InflationRepository
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CalculateInflationViewModel(context, inflationRepository) as T
            }
        }
    }
}