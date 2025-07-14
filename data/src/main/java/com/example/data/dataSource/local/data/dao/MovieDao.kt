package com.example.data.dataSource.local.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.dataSource.local.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity?>

    @Query("SELECT * FROM MOVIE_TABLE WHERE title LIKE :title")
    fun getMovieByTitle(title: String): Flow<MovieEntity?>

    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM MOVIE_TABLE")
    suspend fun deleteAllMovies()
}