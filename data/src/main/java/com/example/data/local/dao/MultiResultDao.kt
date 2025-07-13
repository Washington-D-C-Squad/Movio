package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.MultiResultEntity

@Dao
interface MultiResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<MultiResultEntity>)

    @Query("SELECT * FROM multi_results")
    suspend fun getAll(): List<MultiResultEntity>
} 