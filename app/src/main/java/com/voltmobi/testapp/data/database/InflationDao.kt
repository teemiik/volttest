package com.voltmobi.testapp.data.database

import androidx.room.*
import com.voltmobi.testapp.data.model.Month
import com.voltmobi.testapp.data.model.Year
import com.voltmobi.testapp.data.model.YearsWithMonths

@Dao
interface InflationDao {

    @Insert
    fun insertYear(year: Year) : Long

    @Insert
    fun insertMonth(months: List<Month>)

    @Transaction
    @Query("SELECT * from year")
    fun getYearsWithMonths(): List<YearsWithMonths>

    @Query("SELECT * from month")
    fun getAllMonth(): List<Month>

    @Query("SELECT COUNT(id) FROM year")
    fun getCount(): Int

    @Query("DELETE FROM year")
    fun deleteYear()

    @Query("DELETE FROM month")
    fun deleteMonth()
}