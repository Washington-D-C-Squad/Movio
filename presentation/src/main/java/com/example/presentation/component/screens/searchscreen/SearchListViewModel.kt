package com.example.presentation.component.screens.SearchScreen

import com.example.data.InMemoryRecentSearchRepository
import com.example.presentation.component.search.RecentSearchItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import com.example.domain.RecentSearchItem

class SearchListViewModel(private val repo: InMemoryRecentSearchRepository) : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<RecentSearchItem>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchItem>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        _recentSearches.value = repo.getRecentSearches()
    }

    fun addRecentSearch(item: RecentSearchItem) {
        repo.addRecentSearch(item)
        _recentSearches.value = repo.getRecentSearches()
    }

    fun removeRecentSearch(id: String) {
        repo.removeRecentSearch(id)
        _recentSearches.value = repo.getRecentSearches()
    }

    fun clearAll() {
        repo.clearAllRecentSearches()
        _recentSearches.value = repo.getRecentSearches()
    }
}