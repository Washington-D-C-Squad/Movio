package com.example.presentation.component.searchListUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.data.InMemoryRecentSearchRepository
import com.example.designsystem.AppTheme
import com.example.designsystem.component.textInputField.SearchTextInputField
import com.example.presentation.component.search.RecentSearchHeader
import com.example.presentation.component.search.RecentSearchList

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
            .background(AppTheme.colors.surfaceColor.surface)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTextInputField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            onClear = { searchQuery = "" },
            onImeAction = {
                if (searchQuery.isNotBlank()) {
                    viewModel.addRecentSearch(
                        com.example.domain.RecentSearchItem(
                            id = System.currentTimeMillis(),
                            query = searchQuery
                        )
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(360.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchHeader(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(360.dp),
            onClearAll = { viewModel.clearAll() }
        )
        Spacer(modifier = Modifier.height(4.dp))
        RecentSearchList(
            items = filteredRecentSearches.map { it.query },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(360.dp),
            searchQuery = searchQuery,
            onRemove = { index ->
                val item = filteredRecentSearches.getOrNull(index)
                if (item != null) {
                    viewModel.removeRecentSearch(item.query)
                }
            }
        )
    }
}