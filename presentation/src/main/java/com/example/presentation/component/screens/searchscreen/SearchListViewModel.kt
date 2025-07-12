package com.example.presentation.component.screens.searchscreen


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import com.example.domain.RecentSearchRepository
import com.example.domain.RecentSearchItem
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchListViewModel(private val repo: RecentSearchRepository) : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<RecentSearchItem>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearchItem>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        viewModelScope.launch {
            _recentSearches.value = repo.getRecentSearches()
        }
    }

    fun addRecentSearch(item: RecentSearchItem) {
        viewModelScope.launch {
            repo.addRecentSearch(item)
            loadRecentSearches()
        }
    }

    fun removeRecentSearch(item: RecentSearchItem) {
        viewModelScope.launch {
            repo.removeRecentSearch(item.id.toString())
            loadRecentSearches()
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            repo.clearAllRecentSearches()
            loadRecentSearches()
        }
    }
}