package com.example.expancom2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.expancom2.data.roomdb.dao.CategoryDao
import com.example.expancom2.data.roomdb.dao.CheckDao
import com.example.expancom2.data.roomdb.dao.DateDao
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check

class AppRepository(
    private val checkDao: CheckDao,
    private val categoryDao: CategoryDao) {

    val allCategories: LiveData<List<Category>> = categoryDao.getCategories()
    val allChecks: LiveData<List<Check>> = checkDao.getChecks()

    suspend fun insertCategories(categories: List<Category>) {
        categoryDao.insertCategories(categories)
    }

    suspend fun updateCategory(category: Category) {
        categoryDao.update(category)
    }

    suspend fun insertCheck(check: Check) {
        checkDao.insert(check)
    }

    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun delete(deletedCheck: String) {
        checkDao.deletedCheck(deletedCheck)
    }

    suspend fun deleteAll() {
        checkDao.deleteAll()
    }
}

