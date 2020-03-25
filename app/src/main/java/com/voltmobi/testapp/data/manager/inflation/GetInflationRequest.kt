package com.voltmobi.testapp.data.manager.inflation

import com.voltmobi.testapp.data.manager.BaseResult
import com.voltmobi.testapp.network.api.error.TypeApiError
import java.io.InputStream

interface GetInflationRequest {
    suspend operator fun invoke(): ResultGetInflationRequest

    data class ResultGetInflationRequest(
        val inputStream: InputStream?,
        override val typeApiError: TypeApiError?
    ): BaseResult(typeApiError)
}