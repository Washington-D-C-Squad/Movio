package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.SeriesEntity

@Dao
interface SeriesDao {

    @Upsert
    suspend fun insertSeries(series: SeriesEntity)

    @Delete
    suspend fun deleteSeries(series: SeriesEntity)

    @Query("SELECT * FROM SERIES_TABLE WHERE seriesId = :id")
    suspend fun getSeriesById(id: Int): SeriesEntity?

    @Query("SELECT * FROM SERIES_TABLE WHERE title LIKE :title LIMIT 20")
    suspend fun getSeriesByTitle(title: String): List<SeriesEntity>

    @Query("SELECT * FROM SERIES_TABLE ORDER BY rate DESC")
    suspend fun getTopRatedSeries(): List<SeriesEntity>

    @Query("SELECT * FROM SERIES_TABLE")
    suspend fun getAllSeries(): List<SeriesEntity>

    @Query("DELETE FROM SERIES_TABLE")
    suspend fun deleteAllSeries()
}