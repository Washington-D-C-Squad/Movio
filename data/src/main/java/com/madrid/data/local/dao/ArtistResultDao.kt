package com.madrid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrid.data.local.entity.ArtistResultEntity

@Dao
interface ArtistResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artists: List<ArtistResultEntity>)

    @Query("SELECT * FROM artist_results")
    suspend fun getAll(): List<ArtistResultEntity>
} 