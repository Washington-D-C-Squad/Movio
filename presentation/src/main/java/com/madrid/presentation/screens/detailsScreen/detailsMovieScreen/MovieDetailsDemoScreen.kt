package com.madrid.presentation.screens.detailsScreen.detailsMovieScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.component.CastMember

@Composable
fun MovieDetailsDemoScreen(
    modifier: Modifier = Modifier
) {
    val sampleMovieData = MovieDetailsData(
        id = "1",
        title = "Ballerina",
        originalTitle = "BALLERINA",
        posterUrl = "https://image.tmdb.org/t/p/original/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
        backdropUrl = "https://image.tmdb.org/t/p/original/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
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
            ),
            CastMember(
                id = "5",
                name = "Laurence Fishburne",
                imageUrl = "https://image.tmdb.org/t/p/w500/9H7oVx4b6Z0j3EjLZN9mzcqcJjU.jpg"
            )
        )
    )

//    MovieDetailsScreen(
//        movieData = sampleMovieData,
//        onBackClick = { /* Handle back navigation */ },
//        onShareClick = { /* Handle share */ },
//        onHeartClick = { /* Handle heart click */ },
//        onRateClick = { isRated -> /* Handle rating */ },
//        onPlayClick = { /* Handle play */ },
//        onAddToListClick = { isInList -> /* Handle add to list */ },
//        onSeeAllCastClick = { /* Handle see all cast */ },
//        viewModel = TODO(),
//        modifier = TODO(),
//    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsDemoScreenPreview() {
    AppTheme {
        MovieDetailsDemoScreen()
    }
} 