package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface SearchResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: RecentSearchResultEntity)

    @Query("DELETE FROM search_results WHERE queryKey = :queryKey")
    suspend fun clearResult(queryKey: String)

    @Query("DELETE FROM search_results")
    suspend fun clearAll()
} 