package com.madrid.movio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.domain.entity.Cast
import com.madrid.presentation.component.BottomMediaActions
import com.madrid.presentation.component.CastMember
import com.madrid.presentation.component.TopCastSection
import com.madrid.presentation.component.header.MovieDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewCard
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewsTopbar
import com.madrid.presentation.screens.detailsScreen.similarMovies.SimilarMoviesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovioTheme {
                MovieDetailsScreenPreview()
            }
        }
    }
}

@Composable
fun MovieDetailsScreenPreview() {
    val fakeCasts = listOf(
        Cast(
            id = 1,
            name = "Ana de Armas",
            imageUrl = "https://image.tmdb.org/t/p/w500/3vxvsmYLTf4jnr163SUlBIWX8qx.jpg"
        ),
        Cast(
            id = 2,
            name = "Keanu Reeves",
            imageUrl = "https://image.tmdb.org/t/p/w500/4D0PpNI0km5B9Gk7SZOo6hJxJ9P.jpg"
        ),
        Cast(
            id = 3,
            name = "Ian McShane",
            imageUrl = "https://image.tmdb.org/t/p/w500/9H7oVx4b6Z0j3EjLZN9mzcqcJjU.jpg"
        )
    )

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Theme.color.surfaces.surfaceContainer)
    ) {
        MoviePosterDetailScreen(
            imageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
            modifier = Modifier.fillMaxSize()
        )

        Box(modifier = Modifier.statusBarsPadding()) {
            TopAppBar(null)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(360.dp))

            MovieDetailsHeader(
                movieName = "John Wick: Chapter 4",
                movieCategory = listOf("Action", "Thriller", "Crime"),
                date = "2023",
                time = "169 min",
                rate = "4.5",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            BottomMediaActions(
                onRateClick = {},
                onPlayClick = {},
                onAddToListClick = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            MovioText(
                text = "With the price on his head ever increasing, legendary hit man John Wick takes his fight against the High Table global as he seeks out the most powerful players in the underworld, from New York to Paris to Japan to Berlin.",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.body.mediumMedium14,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TopCastSection(
                castMembers = fakeCasts.map { cast ->
                    CastMember(
                        id = cast.id.toString(),
                        name = cast.name,
                        imageUrl = cast.imageUrl
                    )
                },
                onSeeAllClick = {},
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            ReviewCard(
                reviewerName = "Awkwafina",
                reviewerImageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
                rating = 4.5f,
                date = "June 14, 2025",
                content = "This isn't a film, it's a live action video game with a predictable plot and loads of technologically choreographed CGI to substitute for anything vaguely akin to emotion."
            )
            Spacer(modifier = Modifier.height(32.dp))
            SimilarMoviesScreen(
                onMovieClick = { movie ->
                    // Handle movie selection
                },
                onBackClick = {
                    // Handle back navigation
                }
            )
        }
    }
}

