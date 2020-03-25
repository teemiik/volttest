package com.voltmobi.testapp.network.api

import com.google.gson.Gson
import com.voltmobi.testapp.network.api.error.TypeApiError
import com.voltmobi.testapp.network.api.model.response.ErrorResponse
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

class ApiErrorParser(
        private val gson: Gson
) {
    fun parseError(exception: Throwable) : TypeApiError {
        return when(exception) {
            is SocketTimeoutException -> TypeApiError.TIMEOUT
            is UnknownHostException -> TypeApiError.UNKNOWN_HOST
            is UnknownServiceException -> TypeApiError.UNKNOWN_HOST
            is HttpException -> {
                val errorJson = exception.response()?.errorBody()?.string()
                getStandardApiError { gson.fromJson(errorJson, ErrorResponse::class.java) }
            }
            else -> TypeApiError.UNKNOWN_ERROR
        }
    }

    private fun getStandardApiError(converter: () -> ErrorResponse) : TypeApiError {
        return converter().let {
            if (it.error == null) {
                return@let TypeApiError.UNKNOWN_ERROR
            }

            when (it.error) {
                "invalid_code" -> TypeApiError.INVALID_CODE
                "invalid_token" -> TypeApiError.INVALID_TOKEN
                "user_not_found" -> TypeApiError.USER_NOT_FOUND
                "invalid_user_data" -> TypeApiError.INVALID_USER_DATA
                "access_denied" -> TypeApiError.ACCESS_DENIED
                "unexpected_error" -> TypeApiError.UNEXPECTED_ERROR
                else -> TypeApiError.UNKNOWN_ERROR
            }
        }
    }
}