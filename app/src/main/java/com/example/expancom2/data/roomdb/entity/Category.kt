package com.example.expancom2.data.roomdb.entity

import androidx.room.*

@Entity(tableName = "category_table")
data class Category (
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String?,
    @ColumnInfo var sum: Double,
    @ColumnInfo val color: String
)