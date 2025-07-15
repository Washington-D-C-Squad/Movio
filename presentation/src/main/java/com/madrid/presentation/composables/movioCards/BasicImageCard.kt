package com.madrid.presentation.composables.movioCards


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.R.string

@Composable
fun BasicImageCard(
    imageUrl: String,
    height: Dp,
    width: Dp,
    radius: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(height = height, width = width)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(
                string.moive_image
            ),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(radius)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun prevcand() {
    AppTheme {
        BasicImageCard(
            imageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            height = 180.dp, width = 158.dp, AppTheme.radius.small
        )
    }
}