package com.voltmobi.testapp.data.model

import androidx.room.*

@Entity(
    tableName = "month",
    foreignKeys = [ForeignKey(entity = Year::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("year_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["year_id"])
    ]
)
data class Month (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "year_id")
    val yearId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "value")
    var value: Float
)