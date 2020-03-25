package com.voltmobi.testapp.di.components.repository

import androidx.room.Room
import com.voltmobi.testapp.App
import com.voltmobi.testapp.data.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    app: App
) {
    private var database = Room.databaseBuilder(
            app.getApplicationContext(),
            MVPDatabase::class.java, "mvpdatabase"
        )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): MVPDatabase {
        return database
    }

    @Singleton
    @Provides
    fun providesSessionDao(database: MVPDatabase): InflationDao {
        return database.inflationDao()
    }

}