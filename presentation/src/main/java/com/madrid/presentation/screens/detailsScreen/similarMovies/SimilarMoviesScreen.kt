package com.madrid.presentation.screens.detailsScreen.similarMovies

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.detectImageContent.FilteredImage

data class SimilarMovie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Double
)

@Composable
fun SimilarMoviesSection(
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {},
    onMovieClick: (SimilarMovie) -> Unit = {}
) {
    val fakeMovies = listOf(
        SimilarMovie(
            id = 1,
            title = "Spider-Man: Into the Spider-Verse",
            imageUrl = "https://image.tmdb.org/t/p/w500/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
            rating = 4.8
        ),
        SimilarMovie(
            id = 2,
            title = "The Dark Knight",
            imageUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            rating = 5.0
        ),
        SimilarMovie(
            id = 3,
            title = "Grave of the Fireflies",
            imageUrl = "https://image.tmdb.org/t/p/w500/qG3RYlIVpTYclR9TYIsy8p7m7AT.jpg",
            rating = 4.7
        )
    )

    Column(modifier = modifier) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "Similar Movies",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.headline.mediumMedium18
            )
            
            MovioText(
                text = "See all",
                color = Theme.color.surfaces.onSurfaceVariant,
                textStyle = Theme.textStyle.label.smallRegular14,
                modifier = Modifier.clickable(onClick = onSeeAllClick)
            )
        }

        // Horizontal list of movies
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(fakeMovies) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = { onMovieClick(movie) }
                )
            }
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
            
            // Rating badge
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
                    MovioText(
                        text = "★",
                        color = Theme.color.system.warning,
                        textStyle = Theme.textStyle.label.smallRegular12
                    )
                    MovioText(
                        text = movie.rating.toString(),
                        color = Theme.color.surfaces.onSurface,
                        textStyle = Theme.textStyle.label.smallRegular12
                    )
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
            SimilarMoviesSection()
        }
    }
} 