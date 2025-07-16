package com.madrid.presentation.screens.searchScreen

import HeaderSectionBar
import androidx.annotation.UiThread
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.R
import com.madrid.presentation.composables.movioCards.MovioArtistsCard
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.features.recentSearchLayout.SearchInputSection
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState
import com.madrid.presentation.screens.searchScreen.viewModel.interactionListener.interactionListener
import org.koin.androidx.compose.koinViewModel


@Composable
fun FilteredScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var isRecentSearchActive by remember { mutableStateOf(false) }
    var localSearchQuery by remember { mutableStateOf("") }
    var TypeOfFilterSearch by remember { mutableStateOf("movies") }

    ContentFilteredScreen(
        onChangeTypeOfFilterSearch = {
            TypeOfFilterSearch = it
        },
        typeOfFilterSearch = TypeOfFilterSearch,
        localSearchQuery = localSearchQuery,
        onChangeLocalSearchQuery = {
            localSearchQuery = it
            interactionListener.onSearchQuerySubmitted(it, viewModel)
        },
        onSearchBarClick = {
            isRecentSearchActive = false
        },
        movies = uiState.filteredScreenUiState.movie,
        artist = uiState.filteredScreenUiState.artist,
        series = uiState.filteredScreenUiState.series,
        topRated = uiState.filteredScreenUiState.topResult,
        viewModel = viewModel,
        onSearchMovie = {
            viewModel.searchMovies(localSearchQuery)
//            viewModel.searchFilteredMovies(localSearchQuery)
        },
        onSearchSeries = {
//            viewModel.searc(localSearchQuery)

        },
        onSearchArtist = {

        }
    )
}


@Composable
private fun ContentFilteredScreen(
    onChangeTypeOfFilterSearch :(String)-> Unit ,
    typeOfFilterSearch : String ,
    localSearchQuery : String ,
    onChangeLocalSearchQuery :(String)-> Unit ,
    viewModel: SearchViewModel,
    onSearchBarClick: () -> Unit = {},

    onSearchMovie : ()->Unit ,
    onSearchSeries :()->Unit ,
    onSearchArtist :()-> Unit ,

    topRated: List<SearchScreenState.MovieUiState> = emptyList(),
    movies: List<SearchScreenState.MovieUiState> = emptyList(),
    series: List<SearchScreenState.SeriesUiState> = emptyList(),
    artist: List<SearchScreenState.ArtistUiState> = emptyList(),
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(AppTheme.colors.surfaceColor.onSurface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
    ) {
        item {
            SearchInputSection(
                searchQuery = localSearchQuery,
                onSearchQueryChange = {
                    onChangeLocalSearchQuery(it)
                    when(typeOfFilterSearch){
                        "topRated"->{

                        }
                        "movies"->{
                            onSearchMovie()
                        }
                        "series"->{}
                        else -> {
                            //artist

                        }
                    }
                },
                onSearchBarClick = onSearchBarClick
            )

        }
        item {
            HeaderSectionBar(
                tabs = listOf(
                    stringResource(R.string.top_result),
                    stringResource(R.string.Movies),
                    stringResource(R.string.Series),
                    stringResource(R.string.Artists),
                ),
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                },
            )
        }
        item {
            Crossfade(targetState = selectedTabIndex, label = "TabContentAnimation") { index ->
                when (index) {
                    0 -> if (topRated.isNotEmpty()) {
                        onChangeTypeOfFilterSearch("topRated")
                        TopResult(topRated)
                    }
                    1 -> if (movies.isNotEmpty()) {
                        onChangeTypeOfFilterSearch("movies")
                        onSearchMovie()
                        Movie(movies)
                    }
                    2 -> if (series.isNotEmpty()) {
                        onChangeTypeOfFilterSearch("series")
                        onSearchSeries()
                        Series(series)
                    }
                    3 -> if (artist.isNotEmpty()) {
                        onChangeTypeOfFilterSearch("artist")
                        onSearchArtist()
                        Artist(artist)
                    }
                }
            }

        }
    }
}

@Composable
fun TopResult(
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
            item(span = { GridItemSpan(3) }) {
                SearchResultMessage(items = topRated.size.toString())
            }
            items(topRated) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 101.dp,
                    height = 178.dp,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun Movie(
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
            item(span = { GridItemSpan(3) }) {
                SearchResultMessage(items = movies.size.toString())
            }

            items(movies) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 101.dp,
                    height = 178.dp,
                    onClick = { /* onMovieClick(movie.title) */ }
                )
            }
        }
    }
}

@Composable
fun Series(
    series: List<SearchScreenState.SeriesUiState> = emptyList(),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(bottom = AppTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(3) }){
                SearchResultMessage(items = series.size.toString())
            }
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
fun Artist(
    artists: List<SearchScreenState.ArtistUiState> = emptyList()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(bottom = AppTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(3) }){
                SearchResultMessage(items = artists.size.toString())
            }
            items(artists) { artist ->
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


@Composable
 fun SearchResultMessage(items: String, modifier: Modifier = Modifier) {
    MovioText(
        stringResource(id = R.string.search_result_count, items),
        AppTheme.colors.surfaceColor.onSurfaceVariant,
        AppTheme.textStyle.label.smallRegular14,
        modifier = modifier
    )
}
