package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.madrid.data.dataSource.local.entity.CategoryEntity
import com.madrid.data.dataSource.local.entity.relationship.CategoryWithMovies


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM CATEGORY_TABLE WHERE id = :id")
    fun getCategoryById(id: Int): CategoryEntity?

    @Query("SELECT * FROM CATEGORY_TABLE WHERE categoryTitle = :title")
    fun getCategoryByTitle(title: String): List<CategoryEntity>

    @Query("SELECT * FROM CATEGORY_TABLE")
    fun getAllCategories(): List<CategoryEntity>

    // descending order by searchCount
    @Query("SELECT * FROM CATEGORY_TABLE ORDER BY searchCount DESC")
    fun getAllCategoriesBySearchCount(): List<CategoryEntity>

    @Query("DELETE FROM CATEGORY_TABLE")
    suspend fun deleteAllCategories()

    @Transaction
    @Query("SELECT * FROM CATEGORY_TABLE WHERE categoryTitle = :title")
    fun getPlaylistsWithSongs(title: String): List<CategoryWithMovies>

}