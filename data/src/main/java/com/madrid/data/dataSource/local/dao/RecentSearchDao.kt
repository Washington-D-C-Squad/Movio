package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentSearch(recentSearch: RecentSearchEntity)

    @Delete
    suspend fun deleteRecentSearch(recentSearch: RecentSearchEntity)

    @Query("SELECT * FROM RECENT_SEARCH_TABLE ORDER BY timestamp DESC")
    fun getAllRecentSearches(): Flow<List<RecentSearchEntity>>

    @Query("SELECT query FROM RECENT_SEARCH_TABLE ORDER BY timestamp DESC")
    fun getAllRecentSearchQueries(): Flow<List<String>>

    @Query("DELETE FROM RECENT_SEARCH_TABLE WHERE query = :query")
    suspend fun deleteRecentSearchByQuery(query: String)

    @Query("DELETE FROM RECENT_SEARCH_TABLE")
    suspend fun deleteAllRecentSearches()
} 