package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addRecentSearch(query: String)

     @Query("SELECT * FROM RECENT_TABLE")
     fun getRecentSearches(): Flow<List<RecentSearchEntity>>

     @Query("DELETE FROM RECENT_TABLE WHERE searchQuery = :query")
     suspend fun removeRecentSearch(query: String)

     @Query("DELETE FROM RECENT_TABLE")
     suspend fun clearAllRecentSearches()
}