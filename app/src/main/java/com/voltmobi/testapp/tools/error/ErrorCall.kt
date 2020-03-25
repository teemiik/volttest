package com.voltmobi.testapp.tools.error

import com.voltmobi.testapp.App
import com.voltmobi.testapp.R
import com.voltmobi.testapp.data.manager.BaseResult
import com.voltmobi.testapp.network.api.error.TypeApiError
import javax.inject.Inject

class ErrorCall @Inject constructor(
    private val app: App
) {
    @Throws(ApiError::class)
    fun errorCall(baseResult: BaseResult) {
        val context = app.getApplicationContext()
        if (baseResult.typeApiError != null) {
            when (baseResult.typeApiError) {
                TypeApiError.UNEXPECTED_ERROR -> throw ApiError(context.getString(R.string.http_error_unexpected_error))
                TypeApiError.UNKNOWN_ERROR -> throw ApiError(context.getString(R.string.http_error_unknown_error))
                TypeApiError.UNKNOWN_HOST -> throw ApiError(context.getString(R.string.http_error_internet_error))
                TypeApiError.TIMEOUT -> throw ApiError(context.getString(R.string.http_error_timeout))
                else -> throw ApiError(context.getString(R.string.http_error_unknown_error))
            }
        }
    }
}