package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import HeaderSectionBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.paging.compose.LazyPagingItems
import com.madrid.designsystem.component.LoadingSearchCard
import com.madrid.presentation.R
import com.madrid.presentation.component.movioCards.MovioArtistsCard
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState

fun LazyGridScope.filterSearchScreen(
    typeOfFilterSearch: String,
    onChangeTypeFilterSearch: () -> Unit,
    selectedTabIndex: Int,
    onChangeSelectedTabIndex: (Int) -> Unit,
    topRated: LazyPagingItems<SearchScreenState.MovieUiState>,
    movies: LazyPagingItems<SearchScreenState.MovieUiState>,
    series: LazyPagingItems<SearchScreenState.SeriesUiState>,
    artist: LazyPagingItems<SearchScreenState.ArtistUiState>,
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
        "topRated" -> {
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
                    if (topRated.itemCount == 0) {
                        items(9) {
                            LoadingSearchCard()
                        }
                    }
                    items(count = topRated.itemCount) { index ->
                        MovioVerticalCard(
                            description = topRated[index]!!.title,
                            movieImage = topRated[index]!!.imageUrl,
                            rate = topRated[index]!!.rating,
                            width = 101.dp,
                            height = 178.dp,
                            onClick = { }
                        )
                    }
                }
            }
        }

        "movies" -> {
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
                    if (movies.itemCount == 0) {
                        items(9) {
                            LoadingSearchCard()
                        }
                    }
                    items(count = movies.itemCount) { index ->
                        MovioVerticalCard(
                            description = movies[index]!!.title,
                            movieImage = movies[index]!!.imageUrl,
                            rate = movies[index]!!.rating,
                            width = 100.dp,
                            height = 178.dp,
                            onClick = { }
                        )
                    }
                }
            }
        }

        "series" -> {
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
                    if (series.itemCount == 0) {
                        items(9) {
                            LoadingSearchCard()
                        }
                    }
                    items(count = series.itemCount) { index ->
                        MovioVerticalCard(
                            description = series[index]!!.title,
                            movieImage = series[index]!!.imageUrl,
                            rate = series[index]!!.rating,
                            width = 100.dp,
                            height = 178.dp,
                            onClick = { }
                        )
                    }
                }
            }
        }

        else -> {
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
                    if (artist.itemCount == 0) {
                        items(9) {
                            LoadingSearchCard()
                        }
                    }
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