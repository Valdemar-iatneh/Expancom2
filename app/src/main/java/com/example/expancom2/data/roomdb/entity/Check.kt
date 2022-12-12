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
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val sum: Double,
    @ColumnInfo val date: Date,
    @ColumnInfo val categoryId: Int
)

