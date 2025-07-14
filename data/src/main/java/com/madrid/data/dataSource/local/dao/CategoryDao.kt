package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: MovieCategoryEntity)

    @Delete
    suspend fun deleteCategory(category: MovieCategoryEntity)

    @Query("SELECT * FROM CATEGORY_TABLE WHERE id = :id")
    fun getCategoryById(id: Int): Flow<MovieCategoryEntity?>

    @Query("SELECT * FROM CATEGORY_TABLE WHERE title = :title")
    fun getCategoryByTitle(title: String): Flow<List<MovieCategoryEntity>>

    @Query("SELECT * FROM CATEGORY_TABLE")
    fun getAllCategories(): Flow<List<MovieCategoryEntity>>

    // descending order by searchCount
    @Query("SELECT * FROM CATEGORY_TABLE ORDER BY searchCount DESC")
    fun getAllCategoriesBySearchCount(): Flow<List<MovieCategoryEntity>>

    @Query("DELETE FROM CATEGORY_TABLE")
    suspend fun deleteAllCategories()
}