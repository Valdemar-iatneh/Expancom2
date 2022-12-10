package com.example.expancom2.data.roomdb

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expancom2.data.roomdb.dao.CategoryDao
import com.example.expancom2.data.roomdb.dao.CheckDao
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

        //private fun buildDatabase(context: Context) =
        //    Room.databaseBuilder(context.applicationContext,
        //        DBService::class.java, "Sample.db")
        //        // prepopulate the database after onCreate was called
        //        .addCallback(object : Callback() {
        //            override fun onCreate(db: SupportSQLiteDatabase) {
        //                super.onCreate(db)
        //                // insert the data on the IO Thread
        //                getDB(context).CategoryDao().insertCategories(PREPOPULATE_DATA)
        //            }
        //        })
        //        .build()
        //
        //val PREPOPULATE_DATA = listOf(
        //    Category(1, "Продукты", 0, "#000000"),
        //    Category(2, "Досуг", 0, "#aaaaaa"),
        //    Category(3, "Кафе", 0, "#cccccc"),
        //    Category(4, "Транспорт", 0, "#555555"),
        //    Category(5, "Здоровье", 0, "#eeeeee"),
        //    Category(6, "Здоровье", 0, "#bbbbbb")
        //)
    }
}