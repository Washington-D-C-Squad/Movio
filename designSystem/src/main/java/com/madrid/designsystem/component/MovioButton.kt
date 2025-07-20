package com.madrid.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.madrid.designsystem.AppTheme


@Composable
fun MovioButton(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.brandColors.primary,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Box(

        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.radius.xxLarge))
            .background(color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}