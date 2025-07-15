package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecentSearchLayout(private val recentSearchUseCase: RecentSearchUseCase) : ViewModel() {
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()

    fun loadRecentSearches() {
        viewModelScope.launch {
            recentSearchUseCase.getRecentSearches().collect { searches ->
                _recentSearches.value = searches
            }
        }
    }

    fun addRecentSearch(item: String) {
        viewModelScope.launch {
            recentSearchUseCase.addRecentSearch(item)
            recentSearchUseCase.getRecentSearches().collect { searches ->
                _recentSearches.value = searches
            }
        }
    }

}