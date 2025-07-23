package com.madrid.presentation.screens.detailsScreen.detailsMovieScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.BottomMediaActions
import com.madrid.presentation.component.CastMember
import com.madrid.presentation.component.TopCastSection
import com.madrid.presentation.component.header.MovieDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.screens.detailsScreen.componant.ExpandableDescription
import com.madrid.presentation.screens.detailsScreen.componant.RatingMovieBottomSheet
import com.madrid.presentation.screens.detailsScreen.componant.SaveMovieBottomSheet
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesSection
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    navigateToSimilarMovies: (Int) -> Unit = { _ -> },
    viewModel: DetailsMovieViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    var showRatingBottomSheet by remember { mutableStateOf(false) }
    var showSaveMovieBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

    viewModel.loadData()
    }

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Theme.color.surfaces.surfaceContainer)
    ) {
        MoviePosterDetailScreen(
            imageUrl = uiState.topImageUrl,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.statusBarsPadding()) {
            TopAppBar(
                null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(360.dp))
            MovieDetailsHeader(
                movieName = uiState.movieName,
                movieCategory = uiState.genreMovie,
                date = uiState.dataMovie,
                time = uiState.movieDuration,
                rate = uiState.rate,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            BottomMediaActions(
                onRateClick = { showRatingBottomSheet = true },
                onPlayClick = {},
                onAddToListClick = { showSaveMovieBottomSheet = true },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExpandableDescription(
                description = uiState.description,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            TopCastSection(
                castMembers = uiState.casts.map { cast ->
                    CastMember(
                        id = cast.id.toString(),
                        name = cast.name,
                        imageUrl = "https://image.tmdb.org/t/p/w500${cast.profilePath}"
                    )
                },
                onSeeAllClick = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            ReviewScreen(
                onSeeAllReviews = {},
                uiState = ReviewsScreenUiState()
            )
            Spacer(modifier = Modifier.height(32.dp))
            SimilarMoviesSection(
                movieId = uiState.movieId,
                movieTitle = uiState.movieName,
                onSeeAllClick = { movieId, movieTitle ->
                    navigateToSimilarMovies(movieId)
                },
            )
        }
    }

    if (showRatingBottomSheet) {
        RatingMovieBottomSheet(
            onDismiss = { showRatingBottomSheet = false },
            modifier = Modifier,
            show = true,
            imageUrl = uiState.topImageUrl,
            nameMovie = uiState.movieName
        )
    }

    if (showSaveMovieBottomSheet) {
        SaveMovieBottomSheet(
            onDismiss = { showSaveMovieBottomSheet = false },
            modifier = Modifier,
            show = true,
        )
    }
}