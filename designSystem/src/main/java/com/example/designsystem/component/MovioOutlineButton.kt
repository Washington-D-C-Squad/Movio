package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme

@Composable
fun MovioOutLinedButton(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Box(

        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(AppTheme.radius.xxLarge))
            .border(
                width = 1.dp,
                color = AppTheme.colors.surfaceColor.onSurface_3,
                shape = RoundedCornerShape(AppTheme.radius.xxLarge)
            )
            .fillMaxWidth()
            .background(color)
            .clickable { onClick }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}