package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.dataSource.local.entity.relationship.SeriesGenreCrossRef
import com.madrid.data.dataSource.local.entity.relationship.SeriesWithGenres

@Dao
interface SeriesDao {

    @Upsert
    suspend fun insertSeries(series: SeriesEntity)

    @Delete
    suspend fun deleteSeries(series: SeriesEntity)

    @Query("SELECT * FROM SERIES_TABLE WHERE seriesId = :id")
    suspend fun getSeriesById(id: Int): SeriesEntity?

    @Query("SELECT * FROM SERIES_TABLE WHERE title LIKE :title LIMIT 20 OFFSET :offset")
    suspend fun getSeriesByTitle(title: String, offset: Int): List<SeriesEntity>

    @Query("SELECT * FROM SERIES_TABLE ORDER BY rate DESC")
    suspend fun getTopRatedSeries(): List<SeriesEntity>

    @Query("SELECT * FROM SERIES_TABLE")
    suspend fun getAllSeries(): List<SeriesEntity>

    @Query("DELETE FROM SERIES_TABLE")
    suspend fun deleteAllSeries()

    // Genre
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeriesGenreCrossRef(crossRef: SeriesGenreCrossRef)

    @Transaction
    @Query(
        """
    SELECT DISTINCT SERIES_TABLE.* FROM SERIES_TABLE
    INNER JOIN SeriesGenreCrossRef ON SERIES_TABLE.seriesId = SeriesGenreCrossRef.seriesId
    INNER JOIN SERIES_GENRE_TABLE ON SeriesGenreCrossRef.genreId = SERIES_GENRE_TABLE.genreId
    WHERE SERIES_TABLE.title LIKE :title
    ORDER BY SERIES_GENRE_TABLE.searchCount DESC
    LIMIT 20 OFFSET :offset """
    )
    suspend fun searchSeries(title : String, offset: Int): List<SeriesWithGenres>

}