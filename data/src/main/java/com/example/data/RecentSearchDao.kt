package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecentSearchDao {
    @Query("SELECT * FROM recent_searches ORDER BY timestamp DESC")
    suspend fun getAll(): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RecentSearchEntity)

    @Query("DELETE FROM recent_searches WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM recent_searches")
    suspend fun clearAll()
} 