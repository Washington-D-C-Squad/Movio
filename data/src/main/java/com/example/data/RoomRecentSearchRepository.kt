package com.example.data

import com.example.domain.RecentSearchRepository
import com.example.domain.RecentSearchItem


class RoomRecentSearchRepository(private val dao: RecentSearchDao) : RecentSearchRepository {
    override fun getRecentSearches(): List<RecentSearchItem> = throw UnsupportedOperationException("Use suspend version")
    override fun addRecentSearch(item: RecentSearchItem) = throw UnsupportedOperationException("Use suspend version")
    override fun removeRecentSearch(id: String) = throw UnsupportedOperationException("Use suspend version")
    override fun clearAllRecentSearches() = throw UnsupportedOperationException("Use suspend version")

}
