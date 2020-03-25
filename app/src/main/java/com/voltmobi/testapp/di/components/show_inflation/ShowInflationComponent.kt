package com.voltmobi.testapp.di.components.show_inflation

import android.content.Context
import com.voltmobi.testapp.di.AppProvider
import com.voltmobi.testapp.di.DependencyProvider
import com.voltmobi.testapp.di.qualifiers.FragmentScope
import com.voltmobi.testapp.ui.ShowInflationFragment
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ], modules = [
        ShowInflationModule::class
    ]
)
@FragmentScope
interface ShowInflationComponent {

    fun inject(fragment: ShowInflationFragment)

    class Initializer private constructor() {
        companion object {
            fun init(context: Context): ShowInflationComponent {
                val dependencyProvider = context as DependencyProvider
                return DaggerShowInflationComponent.builder()
                    .appProvider(dependencyProvider.appProvider)
                    .showInflationModule(ShowInflationModule(context))
                    .build()
            }
        }
    }
}