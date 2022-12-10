package com.example.expancom2.data.roomdb.entity

import androidx.room.*

@Entity(tableName = "category_table")
data class Category (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String?,
    @ColumnInfo val sum: Int,
    @ColumnInfo val color: String
)