package com.voltmobi.testapp.di

import com.voltmobi.testapp.data.repository.InflationRepository

interface RepositoryProvider {
    fun getInflationRepository(): InflationRepository
}