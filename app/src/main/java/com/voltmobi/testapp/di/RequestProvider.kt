package com.voltmobi.testapp.di

import com.voltmobi.testapp.data.manager.inflation.*

interface RequestProvider {
    fun getInflationRequest(): GetInflationRequest
}