package com.madrid.presentation.screens.detailsScreen.similarMovies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.madrid.designSystem.component.LoadingSearchCard
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.component.CustomTextTitel
import com.madrid.designSystem.theme.Theme
import com.madrid.domain.entity.SimilarMovie
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import kotlinx.coroutines.flow.MutableStateFlow

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

        similarMovies.itemCount == 0 && similarMovies.loadState.refresh is LoadState.Error -> {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Something went wrong. Please try again.")
                }
            }
        }

        similarMovies.itemCount == 0 && similarMovies.loadState.refresh is LoadState.NotLoading -> {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    MovioText(
                        text = "No similar movies found",
                        color = Theme.color.surfaces.onSurfaceVariant,
                        textStyle = Theme.textStyle.body.mediumMedium14
                    )
                }
            }
        }

        similarMovies.itemCount > 0 -> {
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
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rate.toString(),
                        width = 150.dp,
                        height = 200.dp,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimilarMoviesScreenPreview() {
    val mockMovies = listOf(
        SimilarMovie(
            id = 1,
            title = "The Dark Knight",
            imageUrl = "",
            rate = 9.0
        ),
        SimilarMovie(
            id = 2,
            title = "Inception",
            imageUrl = "",
            rate = 8.8
        ),
        SimilarMovie(
            id = 3,
            title = "Interstellar",
            imageUrl = "",
            rate = 8.6
        )
    )

    // Convert to LazyPagingItems for preview
    val mockPagingItems = MutableStateFlow(PagingData.from(mockMovies)).collectAsLazyPagingItems()

    // Use a Box to simulate the grid scope
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            similarMoviesScreen(
                isLoading = false,
                similarMovies = mockPagingItems,
                onMovieClick = { _ -> },
            )
        }
    }
}