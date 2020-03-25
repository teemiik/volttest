package com.voltmobi.testapp.network.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface InflationApiService {
    @GET("/ytaxi-testing/inflation.csv")
    suspend fun getInflation(): ResponseBody
}