package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.madrid.designSystem.component.EmptySearchLayout
import com.madrid.designSystem.component.HeaderSectionBar
import com.madrid.designSystem.component.LoadingSearchCard
import com.madrid.presentation.R
import com.madrid.presentation.component.movioCards.MovioArtistsCard
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.utils.FilterPagesItem
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState

fun LazyGridScope.filterSearchScreen(
    typeOfFilterSearch: FilterPagesItem,
    onChangeTypeFilterSearch: () -> Unit,
    selectedTabIndex: Int,
    onChangeSelectedTabIndex: (Int) -> Unit,
    topRated: LazyPagingItems<SearchScreenState.MovieUiState>,
    movies: LazyPagingItems<SearchScreenState.MovieUiState>,
    series: LazyPagingItems<SearchScreenState.SeriesUiState>,
    artist: LazyPagingItems<SearchScreenState.ArtistUiState>,
    onSeriesClick: (Int) -> Unit = {}
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        HeaderSectionBar(
            tabs = listOf(
                stringResource(R.string.top_result),
                stringResource(R.string.Movies),
                stringResource(R.string.Series),
                stringResource(R.string.Artists),
            ),
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index ->
                onChangeSelectedTabIndex(index)
                onChangeTypeFilterSearch()
            },
        )
    }

    when (typeOfFilterSearch) {
        FilterPagesItem.TOP_RATED-> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                SearchResultMessage(items = topRated.itemCount.toString())
            }
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(3 * 186.dp)
                ) {
                    when {
                        topRated.itemCount == 0 && topRated.loadState.refresh is LoadState.Loading -> {
                            items(9) {
                                LoadingSearchCard()
                            }
                        }

                        topRated.itemCount == 0 && topRated.loadState.refresh is LoadState.Error -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "Internet is not available",
                                        description = "Please make sure you are connected to the internet and try again.",
                                        image = com.madrid.presentation.R.drawable.img_no_internet
                                    )
                                }
                            }
                        }

                        topRated.itemCount == 0 &&
                                topRated.loadState.refresh is LoadState.NotLoading -> {
                            item (
                                span = { GridItemSpan(maxLineSpan) }
                            ){
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "No results found",
                                        description = "We couldn’t find anything matching your search. Try checking the spelling or explore something else!",
                                        image = com.madrid.presentation.R.drawable.img_no_sesrch_found // Use a "no results" image
                                    )
                                }
                            }
                        }

                        topRated.itemCount > 0 -> {
                            items(count = topRated.itemCount) { index ->
                                MovioVerticalCard(
                                    description = topRated[index]!!.title,
                                    movieImage = topRated[index]!!.imageUrl,
                                    rate = topRated[index]!!.rating,
                                    width = 101.dp,
                                    height = 136.dp,
                                    onClick = { }
                                )
                            }
                        }
                    }
                }
            }
        }

        FilterPagesItem.MOVIES-> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                SearchResultMessage(items = movies.itemCount.toString())
            }
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(3 * 186.dp)
                ) {
                    when {
                        movies.itemCount == 0 && movies.loadState.refresh is LoadState.Loading -> {
                            items(9) {
                                LoadingSearchCard()
                            }
                        }

                        movies.itemCount == 0 && movies.loadState.refresh is LoadState.Error -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "Internet is not available",
                                        description = "Please make sure you are connected to the internet and try again.",
                                        image = com.madrid.presentation.R.drawable.img_no_internet
                                    )
                                }
                            }
                        }

                        movies.itemCount == 0 &&
                                movies.loadState.refresh is LoadState.NotLoading -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }

                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "No results found",
                                        description = "We couldn’t find anything matching your search. Try checking the spelling or explore something else!",
                                        image = com.madrid.presentation.R.drawable.img_no_sesrch_found // Use a "no results" image
                                    )
                                }
                            }
                        }

                        movies.itemCount > 0 -> {
                            items(count = movies.itemCount) { index ->
                                MovioVerticalCard(
                                    description = movies[index]!!.title,
                                    movieImage = movies[index]!!.imageUrl,
                                    rate = movies[index]!!.rating,
                                    width = 101.dp,
                                    height = 136.dp,
                                    onClick = { }
                                )
                            }
                        }
                    }
                }
            }
        }

        FilterPagesItem.SERIES -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                SearchResultMessage(items = series.itemCount.toString())
            }
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(3 * 186.dp)
                ) {
                    when {
                        series.itemCount == 0 && series.loadState.refresh is LoadState.Loading -> {
                            items(9) {
                                LoadingSearchCard()
                            }
                        }

                        series.itemCount == 0 && series.loadState.refresh is LoadState.Error -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "Internet is not available",
                                        description = "Please make sure you are connected to the internet and try again.",
                                        image = com.madrid.presentation.R.drawable.img_no_internet
                                    )
                                }
                            }
                        }

                        series.itemCount == 0 &&
                                series.loadState.refresh is LoadState.NotLoading -> {
                            item (
                                span = { GridItemSpan(maxLineSpan) }

                            ){
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "No results found",
                                        description = "We couldn’t find anything matching your search. Try checking the spelling or explore something else!",
                                        image = com.madrid.presentation.R.drawable.img_no_sesrch_found // Use a "no results" image
                                    )
                                }
                            }
                        }

                        series.itemCount > 0 -> {
                            items(count = series.itemCount) { index ->
                                MovioVerticalCard(
                                    description = series[index]!!.title,
                                    movieImage = series[index]!!.imageUrl,
                                    rate = series[index]!!.rating,
                                    width = 101.dp,
                                    height = 136.dp,
                                    onClick = { onSeriesClick(series[index]!!.id.toInt()) }
                                )
                            }
                        }
                    }
                }
            }
        }

        FilterPagesItem.ARTISTS -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                SearchResultMessage(items = artist.itemCount.toString())
            }
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(3 * 186.dp)
                ) {
                    // Artist
                    when {
                        artist.itemCount == 0 && artist.loadState.refresh is LoadState.Loading -> {
                            items(9) {
                                LoadingSearchCard()
                            }
                        }


                        artist.itemCount == 0 && artist.loadState.refresh is  LoadState.Error -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "Internet is not available",
                                        description = "Please make sure you are connected to the internet and try again.",
                                        image = com.madrid.presentation.R.drawable.img_no_internet
                                    )
                                }
                            }
                        }

                        artist.itemCount == 0 &&
                                artist.loadState.refresh is LoadState.NotLoading -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 64.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    EmptySearchLayout(
                                        title = "No results found",
                                        description = "We couldn’t find anything matching your search. Try checking the spelling or explore something else!",
                                        image = com.madrid.presentation.R.drawable.img_no_sesrch_found // Use a "no results" image
                                    )
                                }
                            }
                        }

                        artist.itemCount > 0 -> {
                            items(count = artist.itemCount) { index ->
                                MovioArtistsCard(
                                    artistsName = artist[index]!!.name,
                                    imageUrl = artist[index]!!.imageUrl,
                                    onClick = { }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}