package com.example.movio.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.movio.component.SearchHeader
import com.example.movio.models.RecentSearchItem
import com.example.movio.component.RecentSearchList
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _recentSearches = MutableLiveData<List<RecentSearchItem>>()
    val recentSearches: LiveData<List<RecentSearchItem>> = _recentSearches

    private val _isLoadingRecent = MutableLiveData(false)
    val isLoadingRecent: LiveData<Boolean> = _isLoadingRecent

    private val _recentSearchError = MutableLiveData<String?>()
    val recentSearchError: LiveData<String?> = _recentSearchError

    fun addToRecentSearches(query: String) {
        viewModelScope.launch {
            val current = _recentSearches.value ?: emptyList()
            val exists = current.any { it.query.equals(query, ignoreCase = true) }
            if (!exists) {
                _recentSearches.value = current + RecentSearchItem(
                    id = System.currentTimeMillis().toString(),
                    query = query,
                    timestamp = System.currentTimeMillis()
                )
            }
        }
    }

    fun removeFromRecentSearches(item: RecentSearchItem) {
        viewModelScope.launch {
            _recentSearches.value = _recentSearches.value?.filter { it.id != item.id }
        }
    }

    fun clearRecentSearches() {
        _recentSearches.value = emptyList()
    }

}

@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel()) {
    val recentSearches by viewModel.recentSearches.observeAsState(emptyList())
    val isLoadingRecent by viewModel.isLoadingRecent.observeAsState(false)
    val recentSearchError by viewModel.recentSearchError.observeAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredRecentSearches = if (searchQuery.isBlank()) recentSearches else recentSearches.filter {
        it.query.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchHeader(
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it },
            onClear = { searchQuery = "" },
            onSubmit = {
                if (searchQuery.isNotBlank()) {
                    viewModel.addToRecentSearches(searchQuery)
                    searchQuery = ""
                }
            },
            onBack = {},
            onFocus = {}
        )
        Spacer(modifier = Modifier.height(8.dp))


        if (isLoadingRecent) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (recentSearchError != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $recentSearchError")
            }
        } else {
            RecentSearchList(
                searchHistory = filteredRecentSearches.map { it.query },
                onSearchItemClick = { selectedQuery -> searchQuery = selectedQuery },
                onRemoveItem = { queryToRemove ->
                    val item = recentSearches.find { it.query == queryToRemove }
                    if (item != null) viewModel.removeFromRecentSearches(item)
                },
                onClearAll = { viewModel.clearRecentSearches() }
            )
        }
    }
}