package com.example.presentation.component.viewModels

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
            loadRecentSearches() // Reload to get updated list
        }
    }

    fun removeRecentSearch(id: Long) {
        viewModelScope.launch {
            recentSearchRepository.removeRecentSearch(id.toString())
            loadRecentSearches() // Reload to get updated list
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            recentSearchRepository.clearAllRecentSearches()
            loadRecentSearches() // Reload to get updated list
        }
    }

    fun clearAll() {
        clearAllRecentSearches()
    }

    fun removeOlderThanOneHour() {
        viewModelScope.launch {
            val oneHourAgo = System.currentTimeMillis() - 60 * 60 * 1000
            val currentSearches = _recentSearches.value
            val searchesToRemove = currentSearches.filter { it.timestamp < oneHourAgo }
            
            searchesToRemove.forEach { search ->
                recentSearchRepository.removeRecentSearch(search.id.toString())
            }
            
            loadRecentSearches() // Reload to get updated list
        }
    }
}