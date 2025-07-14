package com.example.presentation.component.screens.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.textInputField.SearchTextInputField
import com.example.presentation.component.search.RecentSearchHeader
import com.example.presentation.component.search.RecentSearchList
import com.example.presentation.component.searchListUi.SearchListViewModel
import com.example.data.InMemoryRecentSearchRepository

@Composable
fun RecentSearchScreen() {
    val repo = remember { InMemoryRecentSearchRepository() }
    val viewModel: SearchListViewModel = remember { SearchListViewModel(repo) }
    val recentSearches by viewModel.recentSearches.collectAsState()
    val (searchText, setSearchText) = remember { androidx.compose.runtime.mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTextInputField(
            value = searchText,
            onValueChange = setSearchText,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchHeader(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        RecentSearchList(
            items = recentSearches.map { it.query },
            modifier = Modifier.fillMaxWidth()
        )
    }
}