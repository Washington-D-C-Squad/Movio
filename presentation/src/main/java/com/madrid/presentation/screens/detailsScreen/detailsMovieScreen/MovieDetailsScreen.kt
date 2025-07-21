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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.BottomMediaActions
import com.madrid.presentation.component.CastMember
import com.madrid.presentation.component.TopCastSection
import com.madrid.presentation.component.header.MovieDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsMovieViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onHeartClick: () -> Unit = {},
    onRateClick: (Boolean) -> Unit = {},
    onPlayClick: () -> Unit = {},
    onAddToListClick: (Boolean) -> Unit = {},
    onSeeAllCastClick: () -> Unit = {},
    onSeeAllReviewsClick: () -> Unit = {},
    onSeeAllSimilarMoviesClick: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surfaceContainer)
    ) {
        MoviePosterDetailScreen(
            imageUrl = uiState.topImageUrl,
            modifier = Modifier.fillMaxSize()
        )

        Box(modifier = Modifier.statusBarsPadding()) {
            TopAppBar(null)
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(340.dp))

            MovieDetailsHeader(
                movieName = uiState.movieName,
                movieCategory = uiState.genreMovie,
                date = uiState.dataMovie,
                time = uiState.movieDuration,
                rate = uiState.rate.take(3),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            BottomMediaActions(
                onRateClick = onRateClick,
                onPlayClick = onPlayClick,
                onAddToListClick = onAddToListClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            TextWithReadMore(
                description = uiState.description,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TopCastSection(
                castMembers = uiState.casts.map { CastMember(
                    id = it.id.toString(),
                    name = it.name,
                    imageUrl = it.imageUrl
                ) },
                onSeeAllClick = onSeeAllCastClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            ReviewScreen(
                onSeeAllReviews = onSeeAllReviewsClick,
                onSeeAllSimilarMovies = onSeeAllSimilarMoviesClick
            )
        }
    }
}

@Composable
fun TextWithReadMore(description: String, modifier: Modifier) {
    TODO("Not yet implemented")
}