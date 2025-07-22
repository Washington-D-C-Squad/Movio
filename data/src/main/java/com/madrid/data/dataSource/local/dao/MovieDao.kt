package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.relationship.MovieCategoryCrossRef
import com.madrid.data.dataSource.local.entity.relationship.MovieWithCategories

@Dao
interface MovieDao {

    @Upsert
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE WHERE movieId = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM MOVIE_TABLE WHERE title LIKE :title LIMIT 20")
    suspend fun getMovieByTitle(title: String): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE ORDER BY rate DESC")
    suspend fun getTopRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()


    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMovieCategoryCrossRef(crossRef: MovieCategoryCrossRef)

    @Transaction
    @Query(
        """
    SELECT * FROM MOVIE_TABLE 
    INNER JOIN MovieCategoryCrossRef ON MOVIE_TABLE.movieId = MovieCategoryCrossRef.movieId
    INNER JOIN CATEGORY_TABLE ON MovieCategoryCrossRef.categoryTitle = CATEGORY_TABLE.categoryTitle
    ORDER BY CATEGORY_TABLE.searchCount DESC"""
    )
    suspend fun getAllMoviesSortedByCategorySearchCount(): List<MovieWithCategories>

    @Transaction
    @Query("SELECT * FROM MOVIE_TABLE WHERE movieId = :id")
    suspend fun getPlaylistsWithSongs(id: Int): List<MovieWithCategories>
}