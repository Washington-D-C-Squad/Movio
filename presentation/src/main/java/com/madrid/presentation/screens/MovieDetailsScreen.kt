package com.madrid.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.MovieDetailsHeader
import com.madrid.presentation.composables.BottomMediaActions
import com.madrid.presentation.composables.CastMember
import com.madrid.presentation.composables.MovieDescription
import com.madrid.presentation.composables.MovieDetailsNavigationHeader
import com.madrid.presentation.composables.TopCastSection
import com.madrid.presentation.composables.movieActorBackround.MoviePosterDetailScreen

data class MovieDetailsData(
    val id: String,
    val title: String,
    val originalTitle: String,
    val posterUrl: String,
    val backdropUrl: String,
    val genres: List<String>,
    val rating: String,
    val duration: String,
    val releaseDate: String,
    val description: String,
    val castMembers: List<CastMember>
)

@Composable
fun MovieDetailsScreen(
    movieData: MovieDetailsData,
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onHeartClick: () -> Unit = {},
    onRateClick: (Boolean) -> Unit = {},
    onPlayClick: () -> Unit = {},
    onAddToListClick: (Boolean) -> Unit = {},
    onSeeAllCastClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Fixed top navigation header
            MovieDetailsNavigationHeader(
                onBackClick = onBackClick,
                onShareClick = onShareClick,
                onHeartClick = onHeartClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    MoviePosterDetailScreen(
                        ImageUrl = movieData.backdropUrl,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                MovieDetailsHeader(
                    movieName = movieData.title,
                    movieCategory = movieData.genres,
                    date = movieData.releaseDate,
                    time = movieData.duration,
                    rate = movieData.rating,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )

                BottomMediaActions(
                    onRateClick = onRateClick,
                    onPlayClick = onPlayClick,
                    onAddToListClick = onAddToListClick,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )

                MovieDescription(
                    description = movieData.description,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TopCastSection(
                    castMembers = movieData.castMembers,
                    onSeeAllClick = onSeeAllCastClick,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    AppTheme {
        MovieDetailsScreen(
            movieData = MovieDetailsData(
                id = "1",
                title = "Ballerina",
                originalTitle = "BALLERINA",
                posterUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
                backdropUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
                genres = listOf("Action", "Thriller", "Crime"),
                rating = "4.5",
                duration = "2h 5min",
                releaseDate = "06/06/2025",
                description = "Taking place during the events of John Wick: Chapter 3 – Parabellum, Eve Macarro begins her training in the assassin traditions of the Ruska Roma. Derek Kolstad :Characters , Len Wiseman:Director , Shay Hatten",
                castMembers = listOf(
                    CastMember(
                        id = "1",
                        name = "Ana de Armas",
                        imageUrl = "https://image.tmdb.org/t/p/w500/3vxvsmYLTf4jnr163SUlBIWX8qx.jpg"
                    ),
                    CastMember(
                        id = "2",
                        name = "Keanu Reeves",
                        imageUrl = "https://image.tmdb.org/t/p/w500/4D0PpNI0km5B9Gk7SZOo6hJxJ9P.jpg"
                    ),
                    CastMember(
                        id = "3",
                        name = "Ian McShane",
                        imageUrl = "https://image.tmdb.org/t/p/w500/9H7oVx4b6Z0j3EjLZN9mzcqcJjU.jpg"
                    ),
                    CastMember(
                        id = "4",
                        name = "Lance Reddick",
                        imageUrl = "https://image.tmdb.org/t/p/w500/8i6ZDkX1s6tB3iJ9uPxQqR6ZqJ9P.jpg"
                    )
                )
            )
        )
    }
} 