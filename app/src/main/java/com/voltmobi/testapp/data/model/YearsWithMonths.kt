package com.voltmobi.testapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

class YearsWithMonths {
    @Embedded
    lateinit var year: Year

    @Relation(parentColumn = "id", entityColumn = "year_id")
    lateinit var months: List<Month>
}