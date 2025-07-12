package com.example.domain

interface RecentSearchRepository {
    suspend fun getRecentSearches(): List<RecentSearchItem>
    suspend fun addRecentSearch(item: RecentSearchItem)
    suspend fun removeRecentSearch(item: RecentSearchItem)
    suspend fun clearAllRecentSearches()
} 