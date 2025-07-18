package com.madrid.presentation.composables.movieActorBackround

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MoviePosterDetailScreen(
    posterImageUrl: String
) {
    val overlay = Color(0xCC181828)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(372.dp)
                .align(Alignment.TopCenter)
        ) {
            BlurredBackgroundImage(posterImageUrl)
            Box(
                Modifier
                    .matchParentSize()
                    .background(overlay)
            )
            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 260.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black.copy(alpha = 0.7f))
            ) {
                PosterCard(
                    posterImageUrl = posterImageUrl,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePosterDetailScreenPreview() {
    MoviePosterDetailScreen(
        posterImageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
    )
} 