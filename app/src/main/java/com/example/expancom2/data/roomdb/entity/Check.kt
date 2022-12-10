package com.example.expancom2.data.roomdb.entity

import androidx.room.*

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
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val sum: Double,
    @ColumnInfo val day: Int,
    @ColumnInfo val month: Int,
    @ColumnInfo val year: Int,
    @ColumnInfo val hour: Int,
    @ColumnInfo val minute: Int,
    @ColumnInfo val categoryId: Int
)

