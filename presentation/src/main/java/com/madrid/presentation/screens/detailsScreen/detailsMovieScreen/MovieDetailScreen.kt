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
import androidx.navigation.NavController
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.BottomMediaActions
import com.madrid.presentation.component.CastMember
import com.madrid.presentation.component.TopCastSection
import com.madrid.presentation.component.header.MovieDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.detailsScreen.componant.ExpandableDescription
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.screens.detailsScreen.similarMedia.SimilarMovie
import com.madrid.presentation.screens.detailsScreen.similarMedia.SimilarMoviesSection
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: DetailsMovieViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val navController = LocalNavController.current

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
                text = null,
                onFirstIconClick = { navController.navigate(Destinations.SearchScreen)},
                modifier = Modifier.padding(16.dp)
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
                onRateClick = {},
                onPlayClick = {},
                onAddToListClick = {},
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
                        imageUrl = cast.imageUrl
                    )
                },
                onSeeAllClick = {
                    navController.navigate(
                        Destinations.TopCastScreen(
                            mediaId = uiState.movieId.toInt(),
                            isMovie = true
                        )
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            ReviewScreen(
                onSeeAllReviews = {},
                uiState = ReviewsScreenUiState()
            )
            Spacer(modifier = Modifier.height(32.dp))
            SimilarMoviesSection(
                onSeeAllClick = {},
                onMovieClick = { movie ->

                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                movies = uiState.similarMovies.map { movie ->
                    SimilarMovie(
                        id = movie.id,
                        title = movie.name,
                        imageUrl = movie.imageUrl,
                        rating = movie.rate
                    )
                }
            )
        }
    }
}