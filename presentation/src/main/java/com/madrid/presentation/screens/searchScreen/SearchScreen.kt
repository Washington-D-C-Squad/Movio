package com.madrid.presentation.screens.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import com.example.designsystem.component.CustomTextTitel
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.RecentSearchLayout
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var isRecentSearchActive by remember { mutableStateOf(false) }
    if (isRecentSearchActive) { RecentSearchLayout() }
    ContentSearchScreen(
        forYouMovies = uiState.searchUiState.forYouMovies,
        exploreMoreMovies = uiState.searchUiState.exploreMoreMovies,
        searchResults = uiState.searchUiState.searchResults, // <-- add this
        searchQuery = searchQuery, // <-- pass the query
        onSearchQueryChange = { query ->
            searchQuery = query
            viewModel.searchMovies(query)
        },
        onMovieClick = { movie -> },
        isLoading = uiState.searchUiState.isLoading,
        modifier = modifier
    )
    uiState.searchUiState.errorMessage?.let { errorMsg ->
        LaunchedEffect(errorMsg) {
            // handle error
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
}@Composable
fun ContentSearchScreen(
    modifier: Modifier = Modifier,
    forYouMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    exploreMoreMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    searchResults: List<SearchScreenState.MovieUiState> = emptyList(),
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onSearchBarClick: () -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    isLoading: Boolean = false,
) {
    val showSearchResults = searchQuery.isNotBlank()
    val moviesToShow = if (showSearchResults) searchResults else forYouMovies
    LazyColumn(
            modifier = modifier

                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
            item {
                BasicTextInputField(
                    value = searchQuery,
                    onValueChange = {
                        onSearchQueryChange(it)
                        onSearchBarClick()
                    },
                    hintText = "search..",
                    startIconPainter = painterResource(R.drawable.search_normal),
                    endIconPainter = null,

                    modifier = Modifier.fillMaxWidth()
                        .clickable { onSearchBarClick() }
                        .padding(AppTheme.spacing.medium)
                )
            }
            if (!showSearchResults && !isLoading) {
                item {
                    CustomTextTitel(
                        primaryText = "For You",
                        secondaryText = "See all",
                        endIcon = painterResource(R.drawable.outline_alt_arrow_left),
                        onSeeAllClick = {}
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(bottom = AppTheme.spacing.xLarge)
                    ) {
                        items(moviesToShow) { movie ->
                            MovioVerticalCard(
                                description = movie.title,
                                movieImage = movie.imageUrl,
                                rate = movie.rating,
                                width = 160.dp,
                                height = 200.dp,
                                paddingvalue = AppTheme.spacing.small,
                                onClick = { onMovieClick(movie) }
                            )
                        }
                    }
                }
                item {
                    CustomTextTitel(primaryText = "Explore more")
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(bottom = AppTheme.spacing.medium),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(exploreMoreMovies) { movie ->
                                MovioVerticalCard(
                                    description = movie.title,
                                    movieImage = movie.imageUrl,
                                    rate = movie.rating,
                                    width = 180.dp,
                                    height = 200.dp,
                                    onClick = { onMovieClick(movie) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }