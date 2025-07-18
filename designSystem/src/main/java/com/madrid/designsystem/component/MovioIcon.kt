package com.madrid.designsystem.component

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun MovioIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    tint: androidx.compose.ui.graphics.Color
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}