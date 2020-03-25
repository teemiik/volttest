package com.voltmobi.testapp.di.components.network

import com.voltmobi.testapp.tools.utils.Logger
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named

@Module
class HttpConfigModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Named("server_url")
        fun getServerUrl(): String = "https://storage.googleapis.com"

        @JvmStatic
        @Provides
        @Named("http_logging_interceptors")
        fun getHttpLoggingInterceptors(logger: Logger): List<Interceptor> = listOf(
                getHeaderLogging(logger),
                HttpLoggingInterceptor(getBodyLogging(logger)).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
        )

        @JvmStatic
        @Provides
        @Named("http_connection_timeout")
        fun getConnectionTimeout(): Int = 30

        @JvmStatic
        @Provides
        @Named("http_read_timeout")
        fun getReadTimeout(): Int = 60

        private const val maxLength = 800

        private fun getBodyLogging(logger: Logger): HttpLoggingInterceptor.Logger {

            return object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    val length = message.length
                    for (i in 0..length / maxLength) {
                        logger.d("http",
                            message.substring(
                                i * maxLength,
                                if (length < (i + 1) * maxLength) {
                                    length
                                } else {
                                    (i + 1) * maxLength
                                }
                            ))
                    }
                }
            }
        }

        private fun getHeaderLogging(logger: Logger): Interceptor {
            return Interceptor { chain ->
                logger.d("headers", chain.request().headers.toString())
                chain.proceed(chain.request())
            }
        }

    }

}