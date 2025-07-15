package com.madrid.presentation.composables.videoLibrary

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
import androidx.compose.ui.res.stringResource
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@Composable
fun BottomRowForVideoLibrary(
    videosNumber: Number
) {
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
                text = "$videosNumber ${stringResource(com.madrid.presentation.R.string.video_library_video_number)}",
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