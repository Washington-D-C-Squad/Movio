package com.madrid.presentation.composables.movioCards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun MovioArtistsCard(
    imageUrl: String,
    artistsName: String,
    modifier: Modifier = Modifier,
    width: Dp = 102.dp,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(width)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicImageCard(
                imageUrl = imageUrl,
                radius = 100.dp,
                modifier = Modifier.size(88.dp)
            )
            MovioText(
                text = artistsName,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.body.medium14,
                maxLines = 1,
                modifier = Modifier.padding(
                    vertical = AppTheme.spacing.extraSmall,
                    horizontal = AppTheme.spacing.extraSmall
                )
            )
        }
    }
}