package com.example.expancom2.data.roomdb.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "check_table")
data class Check (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String
    //@ColumnInfo val sum: Double,
    //@ColumnInfo val dateTime: Date,
    //@ColumnInfo val category: Int
)