package com.example.expancom2.data.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table")
    fun getCategories(): LiveData<List<Category>>

    //@Query("SELECT * FROM CATEGORY_TABLE WHERE id = :id")
    //fun getCategoryById(id: Int): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categoryList: List<Category>)

    @Update
    suspend fun update(category: Category)
}