package com.voltmobi.testapp.di.components.repository

import com.voltmobi.testapp.data.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun getSessionRepository(impl: InflationRepositoryImpl): InflationRepository

}