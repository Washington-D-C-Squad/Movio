package com.example.presentation.component.movioCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioText

@Composable
fun MovioArtistsCard(
    image: Painter,
    modifier: Modifier = Modifier,
     width: Dp = 102.dp,
    artistsName: String,
) {
    Box(modifier = modifier.width( width)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicImageCard(
                image = image,
                height = 88.dp,
                width = 88.dp,
                radius = 100.dp,
            )
            MovioText(
                text = artistsName,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.body.medium14,
                maxLines = 1,
                modifier = Modifier.padding(vertical = AppTheme.spacing.small, horizontal = AppTheme.spacing.extraSmall)
            )
        }
    }
}