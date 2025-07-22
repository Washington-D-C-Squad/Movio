package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.RecentSearchEntity

@Dao
interface RecentSearchDao {
     @Upsert
     suspend fun addRecentSearch(query: RecentSearchEntity)

     @Query("SELECT * FROM RECENT_TABLE ORDER BY timestamp DESC")
     suspend fun getRecentSearches(): List<RecentSearchEntity>

     @Query("DELETE FROM RECENT_TABLE WHERE searchQuery = :query")
     suspend fun removeRecentSearch(query: String)

     @Query("DELETE FROM RECENT_TABLE")
     suspend fun clearAllRecentSearches()
}