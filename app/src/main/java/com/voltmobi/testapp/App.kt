package com.voltmobi.testapp

import android.app.Application
import android.content.Context
import com.voltmobi.testapp.di.AppProvider
import com.voltmobi.testapp.di.DependencyProvider
import com.voltmobi.testapp.di.MainToolsProvider
import com.voltmobi.testapp.di.components.app.DaggerAppProviderComponent
import com.voltmobi.testapp.di.components.network.DaggerNetworkComponent
import com.voltmobi.testapp.di.components.repository.DaggerRepositoryComponent
import com.voltmobi.testapp.di.components.repository.DatabaseModule
import com.voltmobi.testapp.tools.utils.TimberLogger

interface App {
    fun getApplicationContext(): Context
}

class AppTest : Application(), DependencyProvider {

    override lateinit var appProvider: AppProvider

    class MainTools(private val app: App) : MainToolsProvider {
        override fun getApp(): App = app
    }

    override fun onCreate() {
        super.onCreate()

        val mainToolsProvider = MainTools(this)
        val loggerToolsProvider = TimberLogger()

        val repositoryProvider = DaggerRepositoryComponent
            .builder()
            .app(this)
            .databaseModule(DatabaseModule(this))
            .build()

        val requestProvider = DaggerNetworkComponent.builder()
            .mainToolsProvider(mainToolsProvider)
            .repositoryProvider(repositoryProvider)
            .logger(loggerToolsProvider)
            .build()

        appProvider = DaggerAppProviderComponent
            .builder()
            .mainTools(mainToolsProvider)
            .logger(loggerToolsProvider)
            .requestsProvider(requestProvider)
            .repositoryProvider(repositoryProvider)
            .build()
    }
}