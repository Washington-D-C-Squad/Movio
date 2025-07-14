package com.example.data.dataSource.local.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.dataSource.local.data.entity.MovieCategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: MovieCategoryEntity)

    @Delete
    suspend fun deleteCategory(category: MovieCategoryEntity)

    @Query("SELECT * FROM CATEGORY_TABLE WHERE id = :id")
    fun getCategoryById(id: Int): Flow<MovieCategoryEntity?>

    @Query("SELECT * FROM CATEGORY_TABLE WHERE title = :title")
    fun getCategoryByTitle(title: String): Flow<List<MovieCategoryEntity>>

    @Query("SELECT * FROM CATEGORY_TABLE")
    fun getAllCategories(): Flow<List<MovieCategoryEntity>>

    @Query("DELETE FROM CATEGORY_TABLE")
    suspend fun deleteAllCategories()
}