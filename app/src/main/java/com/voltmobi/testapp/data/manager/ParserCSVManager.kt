package com.voltmobi.testapp.data.manager

import android.content.Context
import com.voltmobi.testapp.data.model.Month
import com.voltmobi.testapp.data.model.Year
import com.voltmobi.testapp.data.repository.InflationRepository
import org.apache.commons.csv.CSVFormat
import java.io.InputStream

class ParserCSVManager (
    private val context: Context,
    private val inflationRepository: InflationRepository
) {
    fun parse(inputStream: InputStream) {
        val reader = inputStream.reader()
        val records = CSVFormat.RFC4180.withFirstRecordAsHeader().withDelimiter(',').parse(reader)
        for (record in records) {
            val year = record.get(0)
            val january = record.get(1)
            val february = record.get(2)
            val march = record.get(3)
            val april = record.get(4)
            val may = record.get(5)
            val june = record.get(6)
            val july = record.get(7)
            val august = record.get(8)
            val september = record.get(9)
            val october = record.get(10)
            val november = record.get(11)
            val december = record.get(12)
            val total = record.get(13)

            val yearId = inflationRepository.insertYear(Year(
                name = year.toInt(),
                value = checkEmpty(total)
            ))


            inflationRepository.insertMonth(
                listOf(
                    Month(
                        yearId = yearId.toInt(),
                        name = "january",
                        value = checkEmpty(january)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "february",
                        value = checkEmpty(february)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "march",
                        value = checkEmpty(march)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "april",
                        value = checkEmpty(april)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "may",
                        value = checkEmpty(may)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "june",
                        value = checkEmpty(june)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "july",
                        value = checkEmpty(july)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "august",
                        value = checkEmpty(august)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "september",
                        value = checkEmpty(september)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "october",
                        value = checkEmpty(october)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "november",
                        value = checkEmpty(november)
                    ), Month(
                        yearId = yearId.toInt(),
                        name = "december",
                        value = checkEmpty(december)
                    )
            ))
        }
        reader.close()
        records.close()
    }

    private fun checkEmpty(str: String) =
        if (str.isNotEmpty())
            str.toFloat()
        else
            0F
}