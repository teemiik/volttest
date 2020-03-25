package com.voltmobi.testapp.di.components.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.voltmobi.testapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun getGson (): Gson {
            return Gson()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun getSharedPreferences(app: App): SharedPreferences {
            return app.getApplicationContext().getSharedPreferences(
                    "repository",
                    Context.MODE_PRIVATE
            )
        }
    }
}