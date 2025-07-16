package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: SeriesEntity)

    @Delete
    suspend fun deleteSeries(series: SeriesEntity)

    @Query("SELECT * FROM SERIES_TABLE WHERE id = :id")
    fun getSeriesById(id: Int): SeriesEntity?

    @Query("SELECT * FROM SERIES_TABLE WHERE title LIKE :title")
    fun getSeriesByTitle(title: String): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_TABLE ORDER BY rate DESC")
    fun getTopRatedSeries(): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Flow<List<SeriesEntity>>

    @Query("DELETE FROM SERIES_TABLE")
    suspend fun deleteAllSeries()
}