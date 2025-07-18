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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.RecentSearchLayout
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.filterSearchScreen
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.forYouAndExploreScreen
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.recentSearchScreen
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel
import com.madrid.presentation.screens.searchScreen.viewModel.interactionListener.interactionListener
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
        .background(AppTheme.colors.surfaceColor.surface),
    viewModel: SearchViewModel = koinViewModel()
) {

    val uiState by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val navController = LocalNavController.current


    var isRecentSearchActive by remember { mutableStateOf(false) }
    if (isRecentSearchActive) {
        RecentSearchLayout()
    }

    ContentSearchScreen(
        modifier = modifier,
        topRated = uiState.filteredScreenUiState.topResult,
        movies = uiState.filteredScreenUiState.movie,
        series = uiState.filteredScreenUiState.series,
        artist = uiState.filteredScreenUiState.artist,
        onClickTopRated = {
            viewModel.topResult(searchQuery)
        },
        onClickMovies = {
            viewModel.searchFilteredMovies(searchQuery)
        },
        onClickSeries = {
            viewModel.searchSeries(searchQuery)
        },
        onClickArtist = {
            viewModel.artists(searchQuery)
        },

        forYouMovies = uiState.searchUiState.forYouMovies,
        exploreMoreMovies = uiState.searchUiState.exploreMoreMovies,
        searchResults = uiState.searchUiState.searchResults,
        searchQuery = searchQuery,
        onSearchQueryChange = { query ->
            searchQuery = query
            viewModel.searchMovies(query)
            viewModel.addRecentSearch(query)
        },
        onMovieClick = { movie ->
            // Navigate to the required Screen --> navController.navigate(Destinations.MovieDetailsScreen)
        },
        isLoading = uiState.searchUiState.isLoading,
        searchHistory = uiState.recentSearchUiState,
        onSearchItemClick = { searchQuery = it },
        onRemoveItem = { viewModel.removeRecentSearch(it) },
        onClearAll = { viewModel.clearAll() },

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
    topRated: List<SearchScreenState.MovieUiState> ,
    movies: List<SearchScreenState.MovieUiState> ,
    series: List<SearchScreenState.SeriesUiState> ,
    artist: List<SearchScreenState.ArtistUiState> ,
    onClickTopRated:()->Unit ,
    onClickMovies:()->Unit ,
    onClickSeries:()->Unit ,
    onClickArtist:()->Unit ,
    modifier: Modifier = Modifier,
    forYouMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    exploreMoreMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    searchResults: List<SearchScreenState.MovieUiState> = emptyList(),
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit,
    onSearchBarClick: () -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    searchHistory: List<String>,
    onSearchItemClick: (String) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearAll: () -> Unit,
    isLoading: Boolean = false,
) {


    val showSearchResults = searchQuery.isNotBlank()
    var typeOfFilterSearch by remember { mutableStateOf("topRated") }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showRecentSearch by remember { mutableIntStateOf(0) }
    LaunchedEffect(searchQuery, typeOfFilterSearch) {
        snapshotFlow { searchQuery }
            .debounce(1000)
            .collect { query ->
                showRecentSearch = 0
                if (query.isNotBlank()) {
                    onSearchBarClick()
                    when (typeOfFilterSearch) {
                        "topRated" -> onClickTopRated()
                        "movies" -> onClickMovies()
                        "series" -> onClickSeries()
                        else -> onClickArtist()
                    }
                }
            }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
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
                    showRecentSearch = 1
                },
                hintText = stringResource(com.madrid.presentation.R.string.search),
                startIconPainter = painterResource(R.drawable.search_normal),
                endIconPainter = painterResource(R.drawable.outline_add),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearchBarClick() }
                    .padding(top = AppTheme.spacing.medium)
            )
        }

        if(searchQuery.isEmpty() && showRecentSearch !=1 ){
            forYouAndExploreScreen(
                showSearchResults = showSearchResults,
                isLoading = isLoading,
                forYouMovies = forYouMovies,
                onMovieClick = onMovieClick,
                exploreMoreMovies = exploreMoreMovies
            )
        }
        if (searchQuery.isNotEmpty() && showRecentSearch != 1) {
            filterSearchScreen(

                typeOfFilterSearch = typeOfFilterSearch,
                topRated = topRated,
                movies = movies,
                series = series,
                artist = artist,

                selectedTabIndex = selectedTabIndex,
                onChangeSelectedTabIndex = { selectedTabIndex = it },
                onChangeTypeFilterSearch = {
                    when (selectedTabIndex) {
                        0 -> {
                            typeOfFilterSearch = "topRated"
                            onClickTopRated()
                        }

                        1 -> {
                            typeOfFilterSearch = "movies"
                            onClickMovies()

                        }

                        2 -> {
                            typeOfFilterSearch = "series"
                            onClickSeries()

                        }

                        else -> {
                            typeOfFilterSearch = "artists"
                            onClickArtist()
                        }
                    }
                }
            )
        }

        if (showRecentSearch == 1) {
            recentSearchScreen(
                searchHistory = searchHistory,
                onSearchItemClick = { onSearchItemClick(it) },
                onRemoveItem = { onRemoveItem(it) },
                onClearAll = { onClearAll() },
            )
        }


    }
}