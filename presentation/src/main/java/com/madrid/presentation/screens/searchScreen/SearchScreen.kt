package com.madrid.presentation.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.RecentSearchLayout
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.forYouAndExploreScreen
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.recentSearchScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
        .background(AppTheme.colors.surfaceColor.surface),
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }


    var isRecentSearchActive by remember { mutableStateOf(false) }
    if (isRecentSearchActive) {
        RecentSearchLayout()
    }
    ContentSearchScreen(
        forYouMovies = uiState.searchUiState.forYouMovies,
        exploreMoreMovies = uiState.searchUiState.exploreMoreMovies,
        searchResults = uiState.searchUiState.searchResults, // <-- add this
        searchQuery = searchQuery, // <-- pass the query
        onSearchQueryChange = { query ->
            searchQuery = query
            viewModel.searchMovies(query)
            viewModel.addRecentSearch(query)
        },
        onMovieClick = { movie -> },
        isLoading = uiState.searchUiState.isLoading,

        searchHistory = uiState.recentSearchUiState,
        onSearchItemClick = { searchQuery = it },
        onRemoveItem = { viewModel.removeRecentSearch(it) },
        onClearAll = { viewModel.clearAll() }
    )
    uiState.searchUiState.errorMessage?.let { errorMsg ->
        LaunchedEffect(errorMsg) {

        }
    }
    if (uiState.searchUiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MovioIcon(
                painter = painterResource(R.drawable.loading),
                contentDescription = "Loading",
                tint = AppTheme.colors.brandColors.primary
            )
        }
    }
}

@Composable
fun ContentSearchScreen(
    modifier: Modifier = Modifier,
    forYouMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    exploreMoreMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    searchResults: List<SearchScreenState.MovieUiState> = emptyList(),
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit ,
    onSearchBarClick: () -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},

    searchHistory: List<String>,
    onSearchItemClick: (String) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearAll: (String) -> Unit,

    isLoading: Boolean = false,
) {


    val showSearchResults = searchQuery.isNotBlank()
    val moviesToShow = if (showSearchResults) searchResults else forYouMovies
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            BasicTextInputField(
                value = searchQuery,
                onValueChange = {
                    onSearchQueryChange(it)
                    onSearchBarClick()
                },
                hintText = "search..",
                startIconPainter = painterResource(R.drawable.search_normal),
                endIconPainter = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearchBarClick() }
                    .padding(top = AppTheme.spacing.medium)
            )
        }
        if(searchQuery.isEmpty()){
            forYouAndExploreScreen(
                showSearchResults = showSearchResults,
                isLoading = isLoading,
                moviesToShow = moviesToShow,
                onMovieClick = onMovieClick,
                exploreMoreMovies = exploreMoreMovies
            )
        }
        if(searchQuery.isNotEmpty()){
            recentSearchScreen(
                searchHistory = searchHistory,
                onSearchItemClick = { onSearchItemClick(it) },
                onRemoveItem = { onRemoveItem(it) },
                onClearAll = { onClearAll },
            )
        }



    }
}