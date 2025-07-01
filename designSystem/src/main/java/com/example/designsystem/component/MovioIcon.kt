package com.example.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun MovioIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    tint: Color
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        tint = tint, modifier = modifier
    )
}