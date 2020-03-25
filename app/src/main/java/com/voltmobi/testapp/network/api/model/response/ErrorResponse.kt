package com.voltmobi.testapp.network.api.model.response

import com.google.gson.annotations.SerializedName

class ErrorResponse(
        @field:SerializedName("error")
        val error: String?
)
