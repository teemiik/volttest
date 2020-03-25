package com.voltmobi.testapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.voltmobi.testapp.data.model.*

@Database(
    entities = [Year::class, Month::class],
    exportSchema = false,
    version = 1
)
abstract class MVPDatabase : RoomDatabase() {
    abstract fun inflationDao(): InflationDao
}