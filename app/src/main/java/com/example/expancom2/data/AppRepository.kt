package com.example.expancom2.data

import androidx.lifecycle.LiveData
import com.example.expancom2.data.roomdb.dao.CheckDao
import com.example.expancom2.data.roomdb.entity.Check

class AppRepository(private val checkDao: CheckDao) {
    val allChecks: LiveData<List<Check>> = checkDao.getChecks()

    suspend fun insert(check: Check) {
        checkDao.insert(check)
    }

    suspend fun delete(deletedCheck: String) {
        checkDao.deletedCheck(deletedCheck)
    }

    suspend fun deleteAll() {
        checkDao.deleteAll()
    }
}