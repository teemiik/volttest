package com.voltmobi.testapp.data.manager.inflation

import com.voltmobi.testapp.tools.error.ErrorCall
import java.io.InputStream
import javax.inject.Inject

interface InflationManager{
    suspend fun getInflation(): InputStream?
}

class InflationManagerImpl @Inject constructor(
    private val errorCall: ErrorCall,
    private val getInflationRequest: GetInflationRequest
): InflationManager {

    override suspend fun getInflation(): InputStream? {
        val result = getInflationRequest()
        errorCall.errorCall(result)
        return result.inputStream
    }
}