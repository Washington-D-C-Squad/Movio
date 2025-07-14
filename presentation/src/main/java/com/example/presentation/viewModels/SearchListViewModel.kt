package com.example.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.RecentSearchItem
import com.example.domain.RecentSearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val recentSearchRepository: RecentSearchRepository
) : ViewModel() {
    
    private val _recentSearches = MutableStateFlow<List<RecentSearchItem>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchItem>> = _recentSearches.asStateFlow()

    init {
        loadRecentSearches()
    }

    private fun loadRecentSearches() {
        viewModelScope.launch {
            _recentSearches.value = recentSearchRepository.getRecentSearches()
        }
    }

    fun addRecentSearch(query: String) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            val newItem = RecentSearchItem(
                id = now,
                query = query,
                timestamp = now
            )
            recentSearchRepository.addRecentSearch(newItem)
            loadRecentSearches()
        }
    }

    fun removeRecentSearch(id: Long) {
        viewModelScope.launch {
            recentSearchRepository.removeRecentSearch(id.toString())
            loadRecentSearches()
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            recentSearchRepository.clearAllRecentSearches()
            loadRecentSearches()
        }
    }

    fun clearAll() {
        clearAllRecentSearches()
    }
}