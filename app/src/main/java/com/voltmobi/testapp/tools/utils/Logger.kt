package com.voltmobi.testapp.tools.utils

interface Logger {
    fun d(message: String)
    fun d(tag: String, message: String)
    fun e(throwable: Throwable)
    fun e(tag: String, throwable: Throwable)
}