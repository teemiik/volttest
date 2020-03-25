package com.voltmobi.testapp.di.components.network

import com.voltmobi.testapp.di.qualifiers.Inflation
import com.voltmobi.testapp.network.api.InflationApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(HttpConfigModule::class)])
class InflationHttpModule {
    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun getInflationApiService(
                @Inflation retrofit: Retrofit): InflationApiService {
            return retrofit.create(InflationApiService::class.java)
        }

        @JvmStatic
        @Provides
        @Inflation
        fun getRetrofit(
                @Named("server_url") serverUrl: String,
                @Named("http_logging_interceptors") httpLoggingInterceptors: List<@JvmSuppressWildcards Interceptor>,
                @Named("http_connection_timeout") connectionTimeout: Int,
                @Named("http_read_timeout") readTimeout: Int
        ): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(serverUrl)
                    .client(getOkHttpClient(
                            httpLoggingInterceptors,
                            connectionTimeout,
                            readTimeout
                    ))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        private fun getOkHttpClient(
                @Named("http_logging_interceptors") httpLoggingInterceptors: List<Interceptor>,
                @Named("http_connection_timeout") connectionTimeout: Int,
                @Named("http_read_timeout") readTimeout: Int
        ): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .method(original.method, original.body)

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }

                for (httpLoggingInterceptor in httpLoggingInterceptors) {
                    addInterceptor(httpLoggingInterceptor)
                }
                connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
                readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            }.build()
        }


    }

}
