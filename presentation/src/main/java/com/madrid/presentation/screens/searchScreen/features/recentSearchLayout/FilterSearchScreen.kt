package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import HeaderSectionBar
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.madrid.presentation.R
import com.madrid.presentation.composables.movioCards.MovioArtistsCard
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.SearchResultMessage
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState

fun LazyGridScope.filterSearchScreen(
    typeOfFilterSearch: String,
    onChangeTypeFilterSearch:()->Unit,

    selectedTabIndex:Int,
    onChangeSelectedTabIndex:(Int)->Unit,

    topRated: LazyPagingItems<SearchScreenState.MovieUiState>,
    movies: List<SearchScreenState.MovieUiState>,
    series: List<SearchScreenState.SeriesUiState>,
    artist: List<SearchScreenState.ArtistUiState>,
) {
    item (
        span = { GridItemSpan(maxLineSpan) }
    ){
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
            ){
                SearchResultMessage(items = topRated.itemCount.toString())
            }
            items(
               count =  topRated.itemCount,
            ) { index ->
                MovioVerticalCard(
                    description = topRated[index]!!.title,
                    movieImage = topRated[index]!!.imageUrl,
                    rate = topRated[index]!!.rating,
                    width = 100.dp,
                    height = 178.dp,
                    onClick = { }
                )
            }
        }

        "movies" -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ){
                SearchResultMessage(items = movies.size.toString())
            }
            items(
                count = movies.size,

            ) { index ->
                MovioVerticalCard(
                    description = movies[index].title,
                    movieImage =  movies[index].imageUrl,
                    rate =  movies[index].rating,
                    width = 100.dp,
                    height = 178.dp,
                    onClick = { /* onMovieClick(movie.title) */ }
                )
            }
        }

        "series" -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ){
                SearchResultMessage(items = series.size.toString())
            }
            items(
                count = series.size,
            ) { index ->
                MovioVerticalCard(
                    description = series[index].title,
                    movieImage =  series[index].imageUrl,
                    rate =  series[index].rating,
                    width = 100.dp,
                    height = 178.dp,
                    onClick = { }
                )
            }
        }

        else -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ){
                SearchResultMessage(items = artist.size.toString())
            }
            items(
               count =  artist.size,
            ) { index ->
                MovioArtistsCard(
                    artistsName = artist[index].name,
                    imageUrl = artist[index].imageUrl,
                    width = 40.dp,
//                    height = 233.dp,
                    onClick = { }
                )
            }
        }
    }


}