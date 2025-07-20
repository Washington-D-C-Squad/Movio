package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrid.presentation.component.RecentSearchList
import com.madrid.presentation.component.header.SearchHeader

@Composable
fun RecentSearchLayout(
    // viewModel: RecentSearchUseCase = RecentSearchUseCase()
) {

}

@Composable
fun SearchScreenContent(
    recentSearches: List<String>,
    loadRecentSearches: () -> Unit,
    addRecentSearch: (String) -> Unit,
    removeRecentSearch: (String) -> Unit,
    clearAll: () -> Unit,
) {

    var searchQuery by remember { mutableStateOf("") }

    val filteredRecentSearches =
        if (searchQuery.isBlank()) recentSearches else recentSearches.filter {
            it.contains(searchQuery, ignoreCase = true)
        }

    LaunchedEffect(Unit) { loadRecentSearches() }

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
                        addRecentSearch(
                            searchQuery
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
            searchHistory = filteredRecentSearches,
            onSearchItemClick = { selectedQuery -> searchQuery = selectedQuery },
            onRemoveItem = { queryToRemove ->
                val item = recentSearches.find { it == queryToRemove }
                if (item != null) {
                    removeRecentSearch(item)
                }
            },
            onClearAll = {
                clearAll()
            }
        )
    }
}