package com.madrid.presentation.component.searchListUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchListViewModel(private val repo: RecentSearchUseCase) : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        viewModelScope.launch {
            repo.getRecentSearches().collect { searches ->
                _recentSearches.value = searches
            }
        }
    }

    fun addRecentSearch(item: String) {
        viewModelScope.launch {
            repo.addRecentSearch(item)
            repo.getRecentSearches().collect { searches ->
                _recentSearches.value = searches
            }
        }
    }

}