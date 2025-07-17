package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.layout.Arrangement
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
import com.example.designsystem.component.CustomTextTitel
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState.MovieUiState

fun LazyGridScope.forYouAndExploreScreen(
    showSearchResults: Boolean,
    isLoading: Boolean,
    forYouMovies: List<MovieUiState>,
    exploreMoreMovies: List<MovieUiState> = emptyList(),
    onMovieClick: (MovieUiState) -> Unit = {},
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
                    .height(333.dp),
            ) {
                items(forYouMovies) { movie ->
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 124.dp,
                        height = 300.dp,
                        paddingvalue = AppTheme.spacing.small,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }
    }
    if (!showSearchResults && exploreMoreMovies.isNotEmpty()) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            CustomTextTitel(
                primaryText = stringResource(com.madrid.presentation.R.string.explore_more)
            )
        }
        items(exploreMoreMovies) { movie ->
            MovioVerticalCard(
                description = movie.title,
                movieImage = movie.imageUrl,
                rate = movie.rating,
                width = 158.dp,
                height = 180.dp,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}