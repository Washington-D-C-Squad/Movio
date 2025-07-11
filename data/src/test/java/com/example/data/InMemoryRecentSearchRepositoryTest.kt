package com.example.data

import com.example.domain.RecentSearchItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InMemoryRecentSearchRepositoryTest {
    private lateinit var repo: InMemoryRecentSearchRepository

    @Before
    fun setup() {
        repo = InMemoryRecentSearchRepository()
    }

    @Test
    fun testAddAndGetRecentSearch() {
        val item = RecentSearchItem("1", "query1", 123L)
        repo.addRecentSearch(item)
        assertEquals(listOf(item), repo.getRecentSearches())
    }

    @Test
    fun testRemoveRecentSearch() {
        val item = RecentSearchItem("1", "query1", 123L)
        repo.addRecentSearch(item)
        repo.removeRecentSearch("1")
        assertTrue(repo.getRecentSearches().isEmpty())
    }

    @Test
    fun testClearAllRecentSearches() {
        repo.addRecentSearch(RecentSearchItem("1", "query1", 123L))
        repo.addRecentSearch(RecentSearchItem("2", "query2", 456L))
        repo.clearAllRecentSearches()
        assertTrue(repo.getRecentSearches().isEmpty())
    }

    @Test
    fun testUniqueQuery() {
        val item1 = RecentSearchItem("1", "query1", 123L)
        val item2 = RecentSearchItem("2", "query1", 456L)
        repo.addRecentSearch(item1)
        repo.addRecentSearch(item2)
        assertEquals(listOf(item2), repo.getRecentSearches())
    }
} 