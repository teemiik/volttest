package com.voltmobi.testapp.data.manager

import com.voltmobi.testapp.network.api.error.TypeApiError

open class BaseResult(
    open val typeApiError: TypeApiError?
)