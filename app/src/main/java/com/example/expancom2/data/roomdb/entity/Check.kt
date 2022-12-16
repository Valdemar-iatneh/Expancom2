package com.example.expancom2.data.roomdb.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "check_table",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        )
    ]
)
data class Check (
    @PrimaryKey var id: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var sum: Double,
    @ColumnInfo var date: Date,
    @ColumnInfo var categoryId: Int
)

