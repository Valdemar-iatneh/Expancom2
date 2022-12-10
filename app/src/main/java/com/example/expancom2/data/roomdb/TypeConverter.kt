package com.example.expancom2.data.roomdb

import java.util.*

class TypeConverter {
    @androidx.room.TypeConverter
    fun fromTimestamp(value: Long): Date? {
        return value?.let { Date(it) }
    }

    @androidx.room.TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}