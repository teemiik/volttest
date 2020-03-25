package com.voltmobi.testapp.network.request

import com.google.gson.Gson
import com.voltmobi.testapp.data.manager.inflation.GetInflationRequest
import com.voltmobi.testapp.network.api.ApiErrorParser
import com.voltmobi.testapp.network.api.InflationApiService
import com.voltmobi.testapp.tools.utils.Logger
import javax.inject.Inject

class GetInflationRequestImpl @Inject constructor(
    private val apiService: InflationApiService,
    private val gson: Gson,
    private val logger: Logger
) : GetInflationRequest {
    override suspend fun invoke(): GetInflationRequest.ResultGetInflationRequest {
        return try {
            val responseBody = apiService.getInflation()
            GetInflationRequest.ResultGetInflationRequest(responseBody.byteStream(), null)
        } catch (e: Exception) {
            logger.e(e)
            GetInflationRequest.ResultGetInflationRequest(null, ApiErrorParser(gson).parseError(e))
        }
    }
}