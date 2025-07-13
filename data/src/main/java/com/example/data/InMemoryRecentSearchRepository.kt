package com.example.data

import com.example.domain.RecentSearchRepository
import com.example.domain.RecentSearchItem

class InMemoryRecentSearchRepository : RecentSearchRepository {
    private val items = mutableListOf<RecentSearchItem>()

    override fun getRecentSearches(): List<RecentSearchItem> = items.toList()

    override fun addRecentSearch(item: RecentSearchItem) {
        items.removeAll { it.query.equals(item.query, ignoreCase = true) }
        items.add(0, item)
    }

    override fun removeRecentSearch(id: String) {
        items.removeAll { it.id.toString() == id }
    }

    override fun clearAllRecentSearches() {
        items.clear()
    }
} 