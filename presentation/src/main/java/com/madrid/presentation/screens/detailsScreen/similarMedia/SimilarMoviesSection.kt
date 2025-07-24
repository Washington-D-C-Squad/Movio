package com.madrid.presentation.screens.detailsScreen.similarMovies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.detectImageContent.FilteredImage
import com.madrid.domain.entity.SimilarMovie
import com.madrid.presentation.R
import com.madrid.presentation.viewModel.detailsViewModel.SimilarMoviesViewModel
import com.madrid.presentation.viewModel.detailsViewModel.createSimilarMoviesViewModel

@Composable
private fun MovieCardPlaceholder() {
    Column(
        modifier = Modifier.width(124.dp)
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                color = Theme.color.brand.primary,
                strokeWidth = 2.dp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(Theme.color.surfaces.surfaceContainer)
        )
    }
}

@Composable
fun SimilarMoviesSection(
    movieId: Int,
    movieTitle: String,
    modifier: Modifier = Modifier,
    onSeeAllClick: (Int, String) -> Unit = { _, _ -> },
    onMovieClick: (Int) -> Unit = {},
    viewModel: SimilarMoviesViewModel = createSimilarMoviesViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadSimilarMovies(movieId)
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "Similar Movies",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.headline.mediumMedium18
            )

            MovioText(
                text = stringResource(id = R.string.see_all),
                color = Theme.color.surfaces.onSurfaceVariant,
                textStyle = Theme.textStyle.label.smallRegular14,
                modifier = Modifier.clickable(onClick = {
                    onSeeAllClick(movieId, movieTitle)
                }))
        }

        when {
            uiState.isLoading -> {
                LoadingSection()
            }

            uiState.movies.isNotEmpty() -> {
                MoviesListSection(
                    movies = uiState.movies,
                    onMovieClick = { movie -> onMovieClick(movie.id) },
                )
            }
        }
    }
}

@Composable
private fun LoadingSection() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        items(5) {
            MovieCardPlaceholder()
        }
    }
}

@Composable
private fun MoviesListSection(
    movies: List<SimilarMovie>,
    onMovieClick: (SimilarMovie) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            MovieCard(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}
@Composable
private fun MovieCard(
    movie: SimilarMovie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(124.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {
            FilteredImage(
                imageUrl = movie.imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            if (movie.rate > 0) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Theme.color.surfaces.surfaceContainer.copy(alpha = 0.7f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MovioIcon(
                            painter = painterResource(id = com.madrid.designSystem.R.drawable.bold_star),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Theme.color.system.warning
                        )
                        MovioText(
                            text = String.format("%.1f", movie.rate),
                            color = Theme.color.surfaces.onSurface,
                            textStyle = Theme.textStyle.label.smallRegular12
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        MovioText(
            text = movie.title,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.label.smallRegular12,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SimilarMoviesSectionPreview() {
    MovioTheme {
        Box(
            modifier = Modifier.background(Theme.color.surfaces.surfaceContainer)
        ) {
            SimilarMoviesSection(
                movieId = 1,
                movieTitle = "Movie Title",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}