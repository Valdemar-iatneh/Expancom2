package com.example.expancom2.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.expancom2.data.AppRepository
import com.example.expancom2.data.roomdb.DBService
import com.example.expancom2.data.roomdb.entity.Check
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppRepository
    val allCheks: LiveData<List<Check>>

    init {
        val checkDao = DBService.getDB(application).CheckDao()
        repository = AppRepository(checkDao)
        allCheks = repository.allChecks
    }

    fun insert(check: Check) {
        viewModelScope.launch {
            repository.insert(check)
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