package com.example.domain

interface RecentSearchRepository {
    fun getRecentSearches(): List<RecentSearchItem>
    fun addRecentSearch(item: RecentSearchItem)
    fun removeRecentSearch(id: String)
    fun clearAllRecentSearches()
} 