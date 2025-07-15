package com.madrid.presentation.screens.searchScreen

import HeaderSectionBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.presentation.composables.movioCards.MovioArtistsCard
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.RecentSearchLayout
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilteredScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val uiState by viewModel.state.collectAsState()
        var searchQuery by remember { mutableStateOf("") }
        var isRecentSearchActive by remember { mutableStateOf(false) }

        ContentFilteredScreen(
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                searchQuery = query
                viewModel.searchMovies(query)
                viewModel.searchSeries(query)
                viewModel.artists(query)
                viewModel.topResult(query)
            },
            onSearchBarClick = {
                isRecentSearchActive = true
            },
            movies = uiState.filteredScreenUiState.movie,
            artist = uiState.filteredScreenUiState.artist,
            series = uiState.filteredScreenUiState.series,
            topRated = uiState.filteredScreenUiState.topResult
        )

        uiState.searchUiState.errorMessage?.let { errorMsg ->
            LaunchedEffect(errorMsg) {
                // handle error
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
    }
}

@Composable
private fun ContentFilteredScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit = {},
    onSearchBarClick: () -> Unit = {},
    topRated: List<SearchScreenState.MovieUiState> = emptyList(),
    movies: List<SearchScreenState.MovieUiState> = emptyList(),
    series: List<SearchScreenState.SeriesUiState> = emptyList(),
    artist: List<SearchScreenState.ArtistUiState> = emptyList(),

    ) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
    ) {
        item {
            BasicTextInputField(
                value = searchQuery,
                onValueChange = {
                    onSearchQueryChange(it)
                    onSearchBarClick()
                },
                hintText = "Search...",
                startIconPainter = painterResource(R.drawable.search_normal),
                endIconPainter = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearchBarClick() }
                    .padding(top = AppTheme.spacing.medium)
            )
        }
        item {
            HeaderSectionBar(
                tabs = listOf(
                    tabs.TopResult.name,
                    tabs.Movies.name,
                    tabs.Series.name,
                    tabs.Artists.name
                ),
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                },
            )
        }
        item {
            when (selectedTabIndex) {
                0 -> if (topRated.isNotEmpty()) TopResult(topRated)
                1 -> if (movies.isNotEmpty()) Movie(movies)
                2 -> if (series.isNotEmpty()) Series(series)
                3 -> if (artist.isNotEmpty()) Artist(artist)
            }
        }
    }
}

@Composable
private fun TopResult(
    topRated: List<SearchScreenState.MovieUiState> = emptyList(),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(bottom = AppTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
            modifier = Modifier.fillMaxSize()
        ) {
            items(topRated) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 160.dp,
                    height = 200.dp,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
private fun Movie(
    movies: List<SearchScreenState.MovieUiState> = emptyList(),

    ) {
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
            items(movies) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 160.dp,
                    height = 200.dp,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
private fun Series(
    series: List<SearchScreenState.SeriesUiState> = emptyList(),

    ) {
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
            items(series) { series ->
                MovioVerticalCard(
                    description = series.title,
                    movieImage = series.imageUrl,
                    rate = series.rating,
                    width = 101.dp,
                    height = 178.dp,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
private fun Artist(
    artist: List<SearchScreenState.ArtistUiState> = emptyList(),

    ) {
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
            items(artist) { artist ->
                MovioArtistsCard(
                    artistsName = artist.name,
                    imageUrl = artist.imageUrl,
                    width = 101.dp,
                    onClick = { }
                )
            }
        }
    }
}

enum class tabs {
    TopResult(),
    Movies,
    Series,
    Artists
}