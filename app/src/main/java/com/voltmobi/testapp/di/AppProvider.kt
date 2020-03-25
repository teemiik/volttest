package com.voltmobi.testapp.di

import com.voltmobi.testapp.App
import com.voltmobi.testapp.data.manager.inflation.InflationManager
import com.voltmobi.testapp.tools.utils.Logger

interface AppProvider :
        MainToolsProvider,
        RepositoryProvider,
        RequestProvider,
        LoggerToolsProvider,
        ManagerProvider

interface MainToolsProvider {
    fun getApp(): App
}

interface LoggerToolsProvider {
    fun getLogger(): Logger
}

interface ManagerProvider{
    fun getInflationManager(): InflationManager
}