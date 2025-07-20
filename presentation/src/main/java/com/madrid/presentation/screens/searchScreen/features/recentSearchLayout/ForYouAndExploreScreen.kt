package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.madrid.designSystem.component.CustomTextTitel
import com.madrid.designSystem.R
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState.MovieUiState
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState

fun LazyGridScope.forYouAndExploreScreen(
    showSearchResults: Boolean,
    isLoading: Boolean,
    forYouMovies: List<SearchScreenState.MovieUiState>,
    exploreMoreMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    onExploreClick: ( LazyPagingItems<SearchScreenState.MovieUiState>) -> Unit = {},
    onClickSeeAll :()->Unit
) {
    if (!showSearchResults && !isLoading) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            CustomTextTitel(
                modifier = Modifier.padding(horizontal = 16.dp),
                primaryText = stringResource(com.madrid.presentation.R.string.for_u),
                secondaryText = stringResource(com.madrid.presentation.R.string.see_all),
                endIcon = painterResource(R.drawable.outline_alt_arrow_left),
                onSeeAllClick = {onClickSeeAll()}
            )
        }
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .height(234.dp),
            ) {
                items(forYouMovies.shuffled()) { movie ->
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 124.dp,
                        height = 177.dp,
                        paddingValue = AppTheme.spacing.small,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }
    }
    if (!showSearchResults && exploreMoreMovies.itemCount != 0 ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            CustomTextTitel(
                primaryText = stringResource(com.madrid.presentation.R.string.explore_more)
            )
        }
        items(
            count = exploreMoreMovies.itemCount,
        ) { index ->
            MovioVerticalCard(
                modifier = Modifier.fillMaxWidth(fraction = 0.50f) ,
                description = exploreMoreMovies[index]!!.title,
                movieImage = exploreMoreMovies[index]!!.imageUrl,
                rate = exploreMoreMovies[index]!!.rating,
                width = 1000.dp,
                height = 222.dp,
                onClick = {
                    onMovieClick(exploreMoreMovies[index]!!)
                }
            )
        }
    }
}