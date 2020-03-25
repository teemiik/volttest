package com.voltmobi.testapp.di.components.calculate_inflation

import android.content.Context
import com.voltmobi.testapp.di.AppProvider
import com.voltmobi.testapp.di.DependencyProvider
import com.voltmobi.testapp.di.qualifiers.FragmentScope
import com.voltmobi.testapp.ui.CalculateInflationFragment
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ], modules = [
        CalculateInflationModule::class
    ]
)
@FragmentScope
interface CalculateInflationComponent {

    fun inject(fragment: CalculateInflationFragment)

    class Initializer private constructor() {
        companion object {
            fun init(context: Context): CalculateInflationComponent {
                val dependencyProvider = context as DependencyProvider
                return DaggerCalculateInflationComponent.builder()
                    .appProvider(dependencyProvider.appProvider)
                    .calculateInflationModule(CalculateInflationModule(context))
                    .build()
            }
        }
    }
}