package com.madrid.presentation.screens.detailsScreen.detailsMovieScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.LoadingSearchCard
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.viewModel.detailsViewModel.SimilarMoviesUiState
import com.madrid.presentation.viewModel.detailsViewModel.SimilarMoviesViewModel
import com.madrid.presentation.viewModel.detailsViewModel.createSimilarMoviesViewModel

@Composable
fun SeeAllSimilarMoviesScreen(
    movieId: Int,
    viewModel: SimilarMoviesViewModel = createSimilarMoviesViewModel(),
) {
    val navController = LocalNavController.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadSimilarMovies(movieId)
    }

    SeeAllSimilarMoviesScreenContent(
        uiState = uiState,
        onClickBackIcon = { navController.popBackStack() },
        onMovieClick = { selectedMovieId ->
            navController.navigate(Destinations.MovieDetailsScreen(selectedMovieId))
        },
        onRetry = {
            viewModel.retry(movieId)
        }
    )
}

@Composable
private fun SeeAllSimilarMoviesScreenContent(
    uiState: SimilarMoviesUiState,
    onClickBackIcon: () -> Unit,
    onMovieClick: (Int) -> Unit = {},
    onRetry: () -> Unit = {}
) {
    val isLoading = uiState.isLoading
    val isError = uiState.errorMessage != null
    val isEmpty = !isLoading && !isError && uiState.movies.isEmpty()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with back button and title
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioIcon(
                    modifier = Modifier.clickable { onClickBackIcon() },
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "Back",
                    tint = Theme.color.surfaces.onSurface
                )
                Spacer(Modifier.width(8.dp))
                MovioText(
                    text = "Similar Movies",
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.headline.largeBold16,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Content based on state
        when {
            isLoading -> {
                items(9) {
                    LoadingSearchCard()
                }
            }

            else -> {
                items(count = uiState.movies.size) { index ->
                    val movie = uiState.movies[index]
                        MovioVerticalCard(
                            modifier = Modifier.fillMaxWidth(),
                            description = movie.title,
                            movieImage = movie.imageUrl,
                            rate = movie.rate.toString(),
                            width = 101.dp,
                            height = 136.dp,
                            onClick = { onMovieClick(movie.id) }
                        )
                }
            }
        }
    }
}