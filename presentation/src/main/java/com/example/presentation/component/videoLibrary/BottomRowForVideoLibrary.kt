package com.example.presentation.component.videoLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText

@Composable
fun BottomRowForVideoLibrary() {
    Box(
        modifier = Modifier.background(color = AppTheme.colors.surfaceColor.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MovioText(
                text = "0 Video",
                color = AppTheme.colors.surfaceColor.onSurfaceVariant,
                textStyle = AppTheme.textStyle.label.smallRegular12,
            )
            MovioIcon(
                painter = painterResource(R.drawable.video_lib),
                tint = Color.Unspecified,
                contentDescription = ""
            )
        }
    }
}