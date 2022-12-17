package com.example.expancom2.data.roomdb

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expancom2.data.roomdb.dao.CategoryDao
import com.example.expancom2.data.roomdb.dao.CheckDao
import com.example.expancom2.data.roomdb.dao.DateDao
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check

@Database(entities = [Check::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class DBService: RoomDatabase() {
    abstract fun CheckDao(): CheckDao
    abstract fun CategoryDao(): CategoryDao

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


