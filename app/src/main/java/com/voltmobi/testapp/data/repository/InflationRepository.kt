package com.voltmobi.testapp.data.repository

import com.voltmobi.testapp.data.database.InflationDao
import com.voltmobi.testapp.data.model.Month
import com.voltmobi.testapp.data.model.Year
import com.voltmobi.testapp.data.model.YearsWithMonths
import javax.inject.Inject

interface InflationRepository {
    fun insertYear(year: Year): Long
    fun insertMonth(months: List<Month>)
    fun getYearsWithMonths(): List<YearsWithMonths>
    fun getAllMonth(): List<Month>
    fun getCount(): Int
    fun deleteYear()
    fun deleteMonth()
}

class InflationRepositoryImpl @Inject constructor(
    private val inflationDao: InflationDao
) : InflationRepository {
    override fun insertYear(year: Year) = inflationDao.insertYear(year)
    override fun insertMonth(months: List<Month>) = inflationDao.insertMonth(months)
    override fun getYearsWithMonths(): List<YearsWithMonths> = inflationDao.getYearsWithMonths()
    override fun getAllMonth(): List<Month> = inflationDao.getAllMonth()
    override fun getCount() = inflationDao.getCount()
    override fun deleteYear() = inflationDao.deleteYear()
    override fun deleteMonth() = inflationDao.deleteMonth()
}