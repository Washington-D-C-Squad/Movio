package com.madrid.presentation.screens.searchScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    // In SearchScreen composable
    ContentSearchScreen(
        forYouMovies = uiState.searchUiState.forYouMovies,
        exploreMoreMovies = uiState.searchUiState.exploreMoreMovies,
        searchResults = uiState.searchUiState.searchResults, // <-- add this
        searchQuery = searchQuery, // <-- pass the query
        onSearchQueryChange = { query ->
            searchQuery = query
            viewModel.searchMovies(query)
        },
        onMovieClick = { movie ->
            //viewModel.navigateToMovieDetails(movie.id)
        },
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
}

@Composable
fun ContentSearchScreen(
    forYouMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    exploreMoreMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    searchResults: List<SearchScreenState.MovieUiState> = emptyList(),
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var localSearchQuery by remember { mutableStateOf(searchQuery) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.surfaceColor.surfaceContainer,
                    RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MovioIcon(
                    painter = painterResource(R.drawable.bold_search_normal),
                    contentDescription = "Search Icon",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
                BasicTextField(
                    value = localSearchQuery,
                    onValueChange = {
                        localSearchQuery = it
                        onSearchQueryChange(it)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    decorationBox = { innerTextField ->
                        if (localSearchQuery.isEmpty()) {
                            MovioText(
                                text = "Search...",
                                color = AppTheme.colors.surfaceColor.onSurface_3,
                                textStyle = AppTheme.textStyle.body.medium14
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }

        val showSearchResults = localSearchQuery.isNotBlank()
        val moviesToShow = when {
            showSearchResults -> searchResults
            else -> forYouMovies
        }

        // Section Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = if (showSearchResults) "Search Results" else "For you",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.headLine.medium18
            )
            if (!showSearchResults) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MovioText(
                        text = "See all",
                        color = AppTheme.colors.surfaceColor.onSurface_2,
                        textStyle = AppTheme.textStyle.body.medium14
                    )
                    MovioIcon(
                        painter = painterResource(R.drawable.outline_alt_arrow_left),
                        contentDescription = "See all arrow",
                        tint = AppTheme.colors.surfaceColor.onSurface_2,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {}
                    )
                }
            }
        }

        if (moviesToShow.isEmpty()) {
            MovioText(
                text = "No movies found",
                color = AppTheme.colors.surfaceColor.onSurface_3,
                textStyle = AppTheme.textStyle.body.medium14,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                items(moviesToShow) { movie ->
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 160.dp,
                        height = 200.dp,
                        paddingvalue = 8.dp,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }

        if (!showSearchResults) {
            MovioText(
                text = "Explore more",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.headLine.medium18,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(exploreMoreMovies) { movie ->
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 160.dp,
                        height = 200.dp,
                        paddingvalue = 8.dp,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }
    }
}