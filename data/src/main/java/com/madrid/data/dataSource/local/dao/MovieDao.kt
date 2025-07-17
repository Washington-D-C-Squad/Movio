package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.madrid.data.dataSource.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM MOVIE_TABLE WHERE title LIKE :title")
    fun getMovieByTitle(title: String): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE ORDER BY rate DESC")
    fun getTopRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()
}