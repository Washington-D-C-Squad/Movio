package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.madrid.data.dataSource.local.entity.MovieGenreEntity


@Dao
interface MovieGenreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenre(category: MovieGenreEntity)

    @Update
    suspend fun updateCategory(category: MovieGenreEntity)

    @Query(
        """
        UPDATE MOVIE_GENRE_TABLE SET searchCount = searchCount + 1 
            WHERE genreTitle = :categoryTitle
     """
    )
    suspend fun increaseCategorySearchCount(categoryTitle: String)

    @Delete
    suspend fun deleteCategory(category: MovieGenreEntity)

    @Query("SELECT * FROM MOVIE_GENRE_TABLE WHERE genreId = :id")
    suspend fun getCategoryById(id: Int): MovieGenreEntity?

    @Query("SELECT * FROM MOVIE_GENRE_TABLE WHERE genreTitle = :title")
    suspend fun getCategoryByTitle(title: String): MovieGenreEntity?

    @Query("SELECT * FROM MOVIE_GENRE_TABLE")
    suspend fun getAllCategories(): List<MovieGenreEntity>

    // descending order by searchCount
    @Query("SELECT * FROM MOVIE_GENRE_TABLE ORDER BY searchCount DESC")
    suspend fun getAllCategoriesBySearchCount(): List<MovieGenreEntity>

    @Query("DELETE FROM MOVIE_GENRE_TABLE")
    suspend fun deleteAllCategories()

}