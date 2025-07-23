package com.madrid.presentation.screens.detailsScreen.similarMovies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.madrid.designSystem.component.CustomTextTitel
import com.madrid.designSystem.component.LoadingSearchCard
import com.madrid.designSystem.component.MovioText
import com.madrid.domain.entity.SimilarMovie
import com.madrid.presentation.component.movioCards.MovioVerticalCard


fun LazyGridScope.similarMoviesScreen(
    isLoading: Boolean,
    similarMovies: LazyPagingItems<SimilarMovie>,
    onMovieClick: (SimilarMovie) -> Unit = {},
) {
    when {
        isLoading -> {
            items(6) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingSearchCard()
                }
            }
        }


        similarMovies.itemCount > 0  -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                CustomTextTitel(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    primaryText = "Similar Movies",
                    secondaryText = "",
                    endIcon = null,
                    onSeeAllClick = { TODO() },
                )
            }
            items(similarMovies.itemCount) { index ->
                similarMovies[index]?.let { movie ->
                    movie.let {
                        MovioVerticalCard(
                            description = it.title.toString(),
                            movieImage = it.imageUrl.toString(),
                            rate = it.rate.toString(),
                            width = 150.dp,
                            height = 200.dp,
                            onClick = { onMovieClick(it) }
                        )
                    }
                }
            }
        }
        else -> {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                MovioText(
                    text = "No similar movies found",
                    color = com.madrid.designSystem.theme.Theme.color.surfaces.onSurfaceVariant,
                    textStyle = com.madrid.designSystem.theme.Theme.textStyle.label.smallRegular12,
                )
            }
        }
    }
}