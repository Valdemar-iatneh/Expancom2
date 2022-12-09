package com.example.expancom2.data.roomdb

import android.content.Context
import androidx.room.*
import com.example.expancom2.data.roomdb.dao.CheckDao
import com.example.expancom2.data.roomdb.entity.Check

@Database(entities = arrayOf(Check::class), version = 1, exportSchema = false)
abstract class DBService: RoomDatabase() {
    abstract fun CheckDao(): CheckDao

    companion object {
        @Volatile
        private var INSTANCE: DBService? = null

        fun getDB(context: Context): DBService {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBService::class.java,
                    "check_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}