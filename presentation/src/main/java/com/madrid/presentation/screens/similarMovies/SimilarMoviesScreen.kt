package com.madrid.presentation.screens.similarMovies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.composables.movioCards.MovioVerticalCard

data class Movie(
    val title: String,
    val rating: Float,
    val posterUrl: String,
    val ageRating: String? = null
)

val moviesList = listOf(
    Movie(
        "The Dark Knight",
        5.0f,
        "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        "16"
    ),
    Movie(
        "Spider-Man: Into the Spider-Verse",
        4.8f,
        "https://image.tmdb.org/t/p/w500/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
        "12"
    ),
    Movie(
        "Grave of the Fireflies",
        4.8f,
        "https://image.tmdb.org/t/p/w500/q0VOH9aVLu5QQG5kumE36e6BjLf.jpg"
    ),
    Movie(
        "À l'instinct - En eaux profondes",
        4.8f,
        "https://image.tmdb.org/t/p/w500/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"
    ),
    Movie(
        "Fullmetal Alchemist: Brotherhood",
        4.8f,
        "https://image.tmdb.org/t/p/w500/4grx6h2B3p6kAMPxQ4vOBFSY6kG.jpg"
    ),
    Movie("Our girl", 4.2f, "https://image.tmdb.org/t/p/w500/2CAL2433ZeIihfX1Hb2139CX0pW.jpg"),
    Movie(
        "Ocean with David Attenborough",
        4.4f,
        "https://image.tmdb.org/t/p/w500/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg"
    ),
    Movie(
        "The Ugly Stepsister",
        4.8f,
        "https://image.tmdb.org/t/p/w500/9O1Iy9od7uGqF3Qh2l1AL5fGd2u.jpg"
    ),
    Movie(
        "Thor: Ragnarok",
        4.5f,
        "https://image.tmdb.org/t/p/w500/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg"
    ),
    Movie(
        "Jurassic World",
        4.0f,
        "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg"
    ),
    Movie("Shark Tank", 4.6f, "https://image.tmdb.org/t/p/w500/6bCplVkhowCjTHXWv49UjRPn0eK.jpg"),
    Movie(
        "Detective Conan",
        4.1f,
        "https://image.tmdb.org/t/p/w500/2vFuG6bWGyQUzYS9d69E5l85nIz.jpg"
    )
)

@Composable
fun SimilarMoviesScreen(
    onBackClick: () -> Unit = {},
    onMovieClick: (Movie) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedMovieId by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                MovioIcon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            MovioText(
                text = "similar movie",
                textStyle = AppTheme.textStyle.headLine.largeBold18,
                color = AppTheme.colors.surfaceColor.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(moviesList) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.posterUrl,
                    rate = movie.rating.toString(),
                    width = 101.33.dp,
                    height = 136.dp,
                    onClick = {
                        selectedMovieId = movie.title
                        onMovieClick(movie)
                    },
                    modifier = Modifier,
                    paddingvalue = 0.dp
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SimilarMoviesScreenPreview() {
    SimilarMoviesScreen(
        onBackClick = {},
        onMovieClick = {}
    )
} 