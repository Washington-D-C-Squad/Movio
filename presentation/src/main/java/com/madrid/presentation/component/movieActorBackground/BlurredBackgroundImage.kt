package com.madrid.presentation.component.movieActorBackground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import com.madrid.detectImageContent.FilteredImage

@Composable
fun BlurredBackgroundImage(
    posterImageUrl: String,
    blurRadius: Dp = 16.dp
) {
    Box(Modifier.fillMaxSize()) {
        FilteredImage(
            imageUrl = posterImageUrl,
            modifier = Modifier
                .fillMaxSize()
                .blur(blurRadius),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
} 