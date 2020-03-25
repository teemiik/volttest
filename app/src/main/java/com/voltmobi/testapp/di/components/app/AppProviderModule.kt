package com.voltmobi.testapp.di.components.app

import com.voltmobi.testapp.data.manager.inflation.InflationManager
import com.voltmobi.testapp.data.manager.inflation.InflationManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface AppProviderModule {
    @Binds
    fun getInflationManager(impl: InflationManagerImpl): InflationManager
}