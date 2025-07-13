package com.example.presentation.component.searchListUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.domain.RecentSearchItem
import com.example.presentation.component.searchUi.component.RecentSearchList
import com.example.presentation.component.searchUi.component.SearchHeader
import com.example.data.InMemoryRecentSearchRepository

@Composable
fun SearchScreen() {
    val repo = remember { InMemoryRecentSearchRepository() }
    val viewModel: SearchListViewModel = remember { SearchListViewModel(repo) }
    val recentSearches by viewModel.recentSearches.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredRecentSearches = if (searchQuery.isBlank()) recentSearches else recentSearches.filter {
        it.query.contains(searchQuery, ignoreCase = true)
    }

    LaunchedEffect(Unit) { viewModel.loadRecentSearches() }

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
                    try {
                        viewModel.addRecentSearch(
                            RecentSearchItem(
                                id = System.currentTimeMillis(),
                                query = searchQuery,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        searchQuery = ""
                    } catch (e: Exception) {
                        println("Error adding recent search: ${e.message}")
                        e.printStackTrace()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchList(
            searchHistory = filteredRecentSearches.map { it.query },
            onSearchItemClick = { selectedQuery -> searchQuery = selectedQuery },
            onRemoveItem = { queryToRemove ->
                val item = recentSearches.find { it.query == queryToRemove }
                if (item != null) {
                    viewModel.removeRecentSearch(item.id.toString())
                }
            },
            onClearAll = {
                viewModel.clearAll()
            }
        )
    }
}