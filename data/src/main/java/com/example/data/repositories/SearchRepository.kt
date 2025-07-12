package com.example.data.repositories

import com.example.domain.RecentSearchRepository
import com.example.domain.RecentSearchItem

class SearchRepository : RecentSearchRepository {
    override suspend fun getRecentSearches(): List<RecentSearchItem> {
        // TODO: Implement actual data source logic
        return emptyList()
    }

    override suspend fun addRecentSearch(item: RecentSearchItem) {
        // TODO: Implement actual data source logic
    }

    override suspend fun removeRecentSearch(item: RecentSearchItem) {
        // TODO: Implement actual data source logic
    }

    override suspend fun clearAllRecentSearches() {
        // TODO: Implement actual data source logic
    }
}