package com.madrid.presentation.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.textInputField.BasicTextInputField
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.EmptyRececntSearch
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.refreshScreenHolder.RefreshScreenHolder
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.filterSearchScreen
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.forYouAndExploreScreen
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.recentSearchScreen
import com.madrid.presentation.screens.searchScreen.utils.FilterPagesItem
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState
import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var typeOfFilterSearch by remember { mutableStateOf(FilterPagesItem.TOP_RATED) }
    val navController = LocalNavController.current


    RefreshScreenHolder(
        refreshState = uiState.searchUiState.refreshState,
        onRefresh = {viewModel.onRefresh(searchQuery , typeOfFilterSearch)}
    ) {
        ContentSearchScreen(
            isError = uiState.searchUiState.isError,
            typeOfFilterSearch = typeOfFilterSearch,
            onSeriesClick = { seriesId ->
                navController.navigate(Destinations.SeasonsScreen(seriesId = seriesId, seasonNumber = 1))
            },
            addRecentSearch = {
                viewModel.addRecentSearch(it)
            },
            modifier = modifier,
            topRated = uiState.filteredScreenUiState.topResult.collectAsLazyPagingItems(),
            movies = uiState.filteredScreenUiState.movie.collectAsLazyPagingItems(),
            series = uiState.filteredScreenUiState.series.collectAsLazyPagingItems(),
            artist = uiState.filteredScreenUiState.artist.collectAsLazyPagingItems(),
            onClickTopRated = {
                typeOfFilterSearch = FilterPagesItem.TOP_RATED
                viewModel.topResult(searchQuery)
            },
            onClickMovies = {
                typeOfFilterSearch = FilterPagesItem.MOVIES
                viewModel.searchFilteredMovies(searchQuery)
            },
            onClickSeries = {
                typeOfFilterSearch = FilterPagesItem.SERIES
                viewModel.searchSeries(searchQuery)
            },
            onClickArtist = {
                typeOfFilterSearch = FilterPagesItem.ARTISTS
                viewModel.artists(searchQuery)
            },

            forYouMovies = uiState.searchUiState.forYouMovies,
            exploreMoreMovies = uiState.searchUiState.exploreMoreMovies.collectAsLazyPagingItems(),
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                searchQuery = query

            },
            onMovieClick = { movie ->
                navController.navigate(Destinations.MovieDetailsScreen(movie.id.toInt()))
            },
            isLoading = uiState.searchUiState.isLoading,
            searchHistory = uiState.recentSearchUiState,
            onSearchItemClick = { searchQuery = it },
            onRemoveItem = { viewModel.removeRecentSearch(it) },
            onClearAll = { viewModel.clearAll() },
            onClickSeeAll = {
                navController.navigate(Destinations.SeeAllForYouScreen)
            },
            highlightrecentSearch = viewModel::highlightCharactersInText

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
                    tint = Theme.color.brand.primary
                )
            }
        }
    }
}


@OptIn(FlowPreview::class)
@Composable
fun ContentSearchScreen(
    isError : Boolean,
    typeOfFilterSearch : FilterPagesItem ,
    addRecentSearch: (String) -> Unit,
    topRated: LazyPagingItems<SearchScreenState.MovieUiState>,
    movies: LazyPagingItems<SearchScreenState.MovieUiState>,
    series: LazyPagingItems<SearchScreenState.SeriesUiState>,
    artist: LazyPagingItems<SearchScreenState.ArtistUiState>,
    onClickTopRated: () -> Unit,
    onClickMovies: () -> Unit,
    onClickSeries: () -> Unit,
    onClickArtist: () -> Unit,
    modifier: Modifier = Modifier,
    forYouMovies: List<SearchScreenState.MovieUiState> = emptyList(),
    exploreMoreMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit,
    onSearchBarClick: () -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    searchHistory: List<String>,
    onSearchItemClick: (String) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearAll: () -> Unit,
    isLoading: Boolean = false,
    onClickSeeAll: () -> Unit,
    onSeriesClick: (Int) -> Unit = {},
    highlightrecentSearch: (String, String, Color, Color, TextStyle) -> AnnotatedString,
) {
    val showSearchResults = searchQuery.isNotBlank()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showRecentSearch by remember { mutableIntStateOf(0) }

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(1000)
            .collect { query ->
                showRecentSearch = 0
                if (query.isNotBlank()) {
                    onSearchBarClick()
                    addRecentSearch(query)
                    when (typeOfFilterSearch) {
                        FilterPagesItem.TOP_RATED-> onClickTopRated()
                        FilterPagesItem.MOVIES-> onClickMovies()
                        FilterPagesItem.SERIES -> onClickSeries()
                        FilterPagesItem.ARTISTS-> onClickArtist()
                    }
                }
            }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding(),
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
                hintText = stringResource(com.madrid.presentation.R.string.searchdot),
                startIconPainter = painterResource(R.drawable.search_normal),
                endIconPainter = painterResource(R.drawable.outline_add),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSearchBarClick() }
                    .padding(top = 16.dp),
                onClickEndIcon = { onSearchQueryChange("") }
            )
        }

        if (searchQuery.isEmpty() && showRecentSearch != 1) {
            forYouAndExploreScreen(
                showSearchResults = showSearchResults,
                isLoading = isLoading,
                isError =  isError,
                forYouMovies = forYouMovies,
                onMovieClick = onMovieClick,
                exploreMoreMovies = exploreMoreMovies,
                onClickSeeAll = { onClickSeeAll() }
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
                            onClickTopRated()
                        }

                        1 -> {
                            onClickMovies()
                        }

                        2 -> {
                            onClickSeries()
                        }

                        else -> {
                            onClickArtist()
                        }
                    }
                },
                onSeriesClick = { seriesId ->
                    onSeriesClick(seriesId)
                }
            )
        }

        if (showRecentSearch == 1 && searchHistory.isNotEmpty()) {
            recentSearchScreen(
                searchHistory = searchHistory,
                searchQuery = searchQuery,
                onSearchItemClick = { onSearchItemClick(it) },
                onRemoveItem = { onRemoveItem(it) },
                onClearAll = { onClearAll() },
                highlightCharactersInText = highlightrecentSearch,
            )
        }
        if (showRecentSearch == 1 && searchHistory.isEmpty()) {
            EmptyRececntSearch()
        }
    }
}