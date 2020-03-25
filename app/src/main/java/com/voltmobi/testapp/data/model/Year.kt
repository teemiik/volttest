package com.voltmobi.testapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "year",
    indices = [
        Index(value = ["id"], unique = true)
    ]
)
data class Year (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    var name: Int,

    @ColumnInfo(name = "value")
    var value: Float
)