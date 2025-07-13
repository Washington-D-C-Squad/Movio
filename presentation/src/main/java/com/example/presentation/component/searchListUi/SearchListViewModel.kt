package com.example.presentation.component.searchListUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecentSearchItem(
    val id: Long,
    val query: String,
    val searchCount: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
)

class SearchListViewModel : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<RecentSearchItem>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchItem>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        // No-op, as state is always up to date in memory
    }

    fun addRecentSearch(query: String) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val newItem = RecentSearchItem(
                id = now,
                query = query,
                timestamp = now
            )
            _recentSearches.value = listOf(newItem) + _recentSearches.value.filter { it.query != query }
        }
    }

    fun removeRecentSearch(id: Long) {
        viewModelScope.launch {
            _recentSearches.value = _recentSearches.value.filter { it.id != id }
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            _recentSearches.value = emptyList()
        }
    }

    fun clearAll() {
        clearAllRecentSearches()
    }

    fun removeOlderThanOneHour() {
        viewModelScope.launch {
            val oneHourAgo = System.currentTimeMillis() - 60 * 60 * 1000
            _recentSearches.value = _recentSearches.value.filter { it.timestamp >= oneHourAgo }
        }
    }
}