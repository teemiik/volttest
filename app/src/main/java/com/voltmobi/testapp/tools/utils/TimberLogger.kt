
package com.voltmobi.testapp.tools.utils

import timber.log.Timber

class TimberLogger : Logger {

        init {
            Timber.plant(Timber.DebugTree())
        }

        override fun d(tag: String, message: String) {
            Timber.tag(tag).d(message)
        }

        override fun e(tag: String, throwable: Throwable) {
            Timber.tag(tag).e(throwable)
        }

        override fun d(message: String) {
            Timber.d(message)
        }

        override fun e(throwable: Throwable) {
            Timber.e(throwable)
        }
    }