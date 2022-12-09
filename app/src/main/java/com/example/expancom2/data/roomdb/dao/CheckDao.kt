package com.example.expancom2.data.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.expancom2.data.roomdb.entity.Check

@Dao
interface CheckDao {
    @Query("SELECT * FROM check_table")
    fun getChecks(): LiveData<List<Check>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(check: Check)

    @Query("DELETE FROM check_table WHERE name =:deletedCheck")
    suspend fun deletedCheck(deletedCheck: String)

    @Query("DELETE FROM check_table")
    suspend fun deleteAll()
}