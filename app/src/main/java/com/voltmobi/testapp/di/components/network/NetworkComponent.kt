package com.voltmobi.testapp.di.components.network

import com.google.gson.Gson
import com.voltmobi.testapp.data.manager.inflation.*
import com.voltmobi.testapp.di.MainToolsProvider
import com.voltmobi.testapp.di.RepositoryProvider
import com.voltmobi.testapp.di.RequestProvider
import com.voltmobi.testapp.network.request.*
import com.voltmobi.testapp.tools.utils.Logger
import dagger.*
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [
            MainToolsProvider::class,
            RepositoryProvider::class
        ],
        modules = [
            NetworkTools::class,
            NetworkModule::class,
            InflationHttpModule::class
        ])
interface NetworkComponent :
        RequestProvider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun logger(loggeImpl: Logger): Builder
        fun mainToolsProvider(mainToolsProvideImpl: MainToolsProvider): Builder
        fun repositoryProvider(repositoryProvideImpl: RepositoryProvider): Builder

        fun build(): RequestProvider
    }

}

@Module
class NetworkTools {
    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun getGson() = Gson()
    }
}

@Module
interface NetworkModule {
    @Binds fun getInflationRequest(impl: GetInflationRequestImpl): GetInflationRequest
}