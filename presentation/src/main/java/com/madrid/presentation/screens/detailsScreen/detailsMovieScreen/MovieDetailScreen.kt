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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.domain.entity.SimilarMovie
import com.madrid.presentation.component.BottomMediaActions
import com.madrid.presentation.component.header.MovieDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesUiState
import com.madrid.presentation.screens.detailsScreen.componant.ExpandableDescription
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewsScreenUiState
import com.madrid.presentation.screens.detailsScreen.similarMedia.SimilarMoviesSection
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesViewModel
import com.madrid.presentation.screens.detailsScreen.similarMovies.createSimilarMoviesViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import org.koin.androidx.compose.koinViewModel
@Composable
fun MovieDetailsScreen(
    viewModel: DetailsMovieViewModel = koinViewModel(),
    similarMoviesViewModel: SimilarMoviesViewModel = createSimilarMoviesViewModel()
) {
    val navController = LocalNavController.current
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    val similarMoviesUiState by similarMoviesViewModel.uiState.collectAsState()
    LaunchedEffect(uiState.movieId) {
        similarMoviesViewModel.loadSimilarMovies(uiState.movieId)
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
                onFirstIconClick = { /* Other action */ },
                onSecondIconClick = {
                    navController.navigate(Destinations.ShareBottomSheetDestination)
                },
                onThirdIconClick = {
                    navController.navigate(Destinations.RatingMovieBottomSheetDestination(
                        imageUrl = uiState.topImageUrl,
                        nameMovie = uiState.movieName
                    ))},
                text = uiState.movieName,
                firstIcon = R.drawable.arrow_left,
                secondIcon = R.drawable.share_arrow,
                thirdIcon = R.drawable.outline_heart,
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
                onRateClick = {
                    navController.navigate(Destinations.RatingMovieBottomSheetDestination(
                        imageUrl = uiState.topImageUrl,
                        nameMovie = uiState.movieName
                    ))

                },
                onPlayClick = {},
                onAddToListClick = {
                    navController.navigate(Destinations.SaveMovieBottomSheetDestination)
                },
                onShareClick = {
                    navController.navigate(Destinations.ShareBottomSheetDestination)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExpandableDescription(
                description = uiState.description,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            ReviewScreen(
                onSeeAllReviews = {
                    navController.navigate(Destinations.ReviewsScreen)
                },
                uiState = ReviewsScreenUiState()
            )
            Spacer(modifier = Modifier.height(32.dp))
            SimilarMoviesSection(
                onSeeAllClick = {
                    navController.navigate(Destinations.SeeAllForYouScreen)
                },
                onMovieClick = { movie ->
                    navController.navigate(Destinations.MovieDetailsScreen(movie.id))
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                movies = similarMoviesUiState.movies
            )
        }
    }
}