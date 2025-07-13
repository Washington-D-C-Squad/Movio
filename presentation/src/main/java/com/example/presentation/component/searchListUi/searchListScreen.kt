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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.search.RecentSearchHeader
import com.example.designsystem.component.search.RecentSearchList
import com.example.designsystem.component.textInputField.SearchTextInputField

@Composable
fun SearchScreen() {
    val viewModel = remember { SearchListViewModel() }
    val recentSearches by viewModel.recentSearches.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val filteredRecentSearches = if (searchQuery.isBlank()) recentSearches else recentSearches.filter {
        it.query.contains(searchQuery, ignoreCase = true)
    }
    fun submitSearch(query: String) {
        if (query.isNotBlank() && (recentSearches.isEmpty() || recentSearches.first().query != query)) {
            viewModel.addRecentSearch(query)
        }
        keyboardController?.hide()
    }
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
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(360.dp),
            imeAction = ImeAction.Done,
            onImeAction = { submitSearch(searchQuery) }
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
            onRemove = { index ->
                val item = filteredRecentSearches.getOrNull(index)
                if (item != null) {
                    viewModel.removeRecentSearch(item.id)
                }
            },
            onItemClick = { index ->
                val item = filteredRecentSearches.getOrNull(index)
                if (item != null) {
                    searchQuery = item.query
                }
            }
        )
    }
}