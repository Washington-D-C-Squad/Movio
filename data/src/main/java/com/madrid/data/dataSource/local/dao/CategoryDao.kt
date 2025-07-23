package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.CategoryEntity
import com.madrid.data.dataSource.local.entity.relationship.CategoryWithMovies


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Query(
        """
        UPDATE CATEGORY_TABLE SET searchCount = searchCount + 1 
            WHERE categoryTitle = :categoryTitle
     """
    )
    suspend fun increaseCategorySearchCount(categoryTitle: String)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM CATEGORY_TABLE WHERE categoryId = :id")
    suspend fun getCategoryById(id: Int): CategoryEntity?

    @Query("SELECT * FROM CATEGORY_TABLE WHERE categoryTitle = :title")
    suspend fun getCategoryByTitle(title: String): CategoryEntity?

    @Query("SELECT * FROM CATEGORY_TABLE")
    suspend fun getAllCategories(): List<CategoryEntity>

    // descending order by searchCount
    @Query("SELECT * FROM CATEGORY_TABLE ORDER BY searchCount DESC")
    suspend fun getAllCategoriesBySearchCount(): List<CategoryEntity>

    @Query("DELETE FROM CATEGORY_TABLE")
    suspend fun deleteAllCategories()

    @Transaction
    @Query("SELECT * FROM CATEGORY_TABLE WHERE categoryTitle = :title")
    suspend fun getPlaylistsWithSongs(title: String): List<CategoryWithMovies>
}