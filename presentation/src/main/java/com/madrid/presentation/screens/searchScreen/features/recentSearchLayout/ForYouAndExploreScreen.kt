package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.designsystem.component.CustomTextTitel
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState.MovieUiState

fun LazyGridScope.forYouAndExploreScreen(
    showSearchResults: Boolean,
    isLoading: Boolean,
    forYouMovies: List<MovieUiState>,
    exploreMoreMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    onMovieClick: (MovieUiState) -> Unit = {},
    onExploreClick: ( LazyPagingItems<SearchScreenState.MovieUiState>) -> Unit = {},
) {
    if (!showSearchResults && !isLoading) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            CustomTextTitel(
                modifier = Modifier.padding(horizontal = AppTheme.spacing.medium),
                primaryText = stringResource(com.madrid.presentation.R.string.for_u),
                secondaryText = stringResource(com.madrid.presentation.R.string.see_all),
                endIcon = painterResource(R.drawable.outline_alt_arrow_left),
                onSeeAllClick = {}
            )
        }
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(
                        bottom = AppTheme.spacing.xLarge
                    )
                    .height(233.dp),
            ) {
                items(forYouMovies) { movie ->
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
                width = 500.dp,
                height = 233.dp,
                onClick = { onExploreClick(exploreMoreMovies) }
            )
        }
    }
}