package com.example.expancom2.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.expancom2.data.AppRepository
import com.example.expancom2.data.roomdb.DBService
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppRepository
    val allCategories: LiveData<List<Category>>
    val allChecks: LiveData<List<Check>>

    init {
        val checkDao = DBService.getDB(application).CheckDao()
        val categoryDao = DBService.getDB(application).CategoryDao()
        repository = AppRepository(checkDao, categoryDao)
        allChecks = repository.allChecks
        allCategories = repository.allCategories
    }

    fun getCategory(id: Int) {
        viewModelScope.launch {
            repository.getCategoryById(id)
        }
    }

    fun insertCheck(check: Check) {
        viewModelScope.launch {
            repository.insertCheck(check)
        }
    }

    fun insertCategory(category: Category) {
        viewModelScope.launch {
            repository.insertCategory(category)
        }
    }

    fun insertCategories(categories: List<Category>) {
        viewModelScope.launch {
            repository.insertCategories(categories)
        }
    }

    fun delete() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun deleteCheck(deletedCheck: String) {
        viewModelScope.launch {
            repository.delete(deletedCheck)
        }
    }

}