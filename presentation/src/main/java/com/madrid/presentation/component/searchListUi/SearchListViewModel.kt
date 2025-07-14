package com.madrid.presentation.component.searchListUi

import androidx.lifecycle.ViewModel
import com.madrid.data.InMemoryRecentSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchListViewModel(private val repo: InMemoryRecentSearchRepository) : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        _recentSearches.value = repo.getRecentSearches()
    }

    fun addRecentSearch(item: String) {
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