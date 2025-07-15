package com.madrid.presentation.component.screens.searchScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.worker.RecentSearchSyncWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val recentSearchUseCase: RecentSearchUseCase
) : ViewModel()  {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        refreshRecentSearches()
    }

    fun refreshRecentSearches() {
        viewModelScope.launch {
            val flow = recentSearchUseCase.getRecentSearches()
            flow.collect { searches ->
                _uiState.value = _uiState.value.copy(recentSearches = searches)
            }
        }
    }

    fun clearRecentSearchesWithWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<RecentSearchSyncWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
        // Optionally refresh after worker runs
        refreshRecentSearches()
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onRecentSearchItemClick(index: Int) {
        _searchQuery.value = uiState.value.recentSearches.getOrNull(index) ?: ""
    }

    fun removeRecentSearch(index: Int) {
        val item = uiState.value.recentSearches.getOrNull(index)
        if (item != null) {
            viewModelScope.launch {
                recentSearchUseCase.removeRecentSearch(item)
                refreshRecentSearches()
            }
        }
    }
}
