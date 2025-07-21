package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.MovieEntity
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
    fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM MOVIE_TABLE WHERE title LIKE :title LIMIT 20")
    fun getMovieByTitle(title: String): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE ORDER BY rate DESC")
    fun getTopRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()

    @Transaction
    @Query("SELECT * FROM MOVIE_TABLE WHERE movieId = :id")
    fun getPlaylistsWithSongs(id : Int): List<MovieWithCategories>
}