package com.voltmobi.testapp.di.components.app

import com.voltmobi.testapp.di.AppProvider
import com.voltmobi.testapp.di.MainToolsProvider
import com.voltmobi.testapp.di.RepositoryProvider
import com.voltmobi.testapp.di.RequestProvider
import com.voltmobi.testapp.tools.utils.Logger
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        MainToolsProvider::class,
        RepositoryProvider::class,
        RequestProvider::class
    ],
    modules = [AppProviderModule::class]
)
interface AppProviderComponent : AppProvider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun logger(logger: Logger): Builder
        fun requestsProvider(requestProvider: RequestProvider): Builder
        fun repositoryProvider(repositoryProvider: RepositoryProvider): Builder
        fun mainTools(mainToolsProvider: MainToolsProvider): Builder
        fun build(): AppProviderComponent
    }
}