package com.madrid.presentation.component.movioCards


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.component.filteredImage.FilteredImage

@Composable
fun BasicImageCard(
    imageUrl: String,
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(height = height, width = width)
    ) {
        FilteredImage(
            imageUrl = imageUrl,
            contentDescription = stringResource(
                com.madrid.presentation.R.string.moive_image
            ),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(AppTheme.radius.small)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun prevcand() {
    AppTheme {
        BasicImageCard(
            imageUrl = painterResource(com.madrid.designsystem.R.drawable.film_photo_sample).toString(),
            height = 180.dp, width = 158.dp
        )
    }
}