package com.example.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioButton
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.brandColors.primary,
    isLoading: Boolean = false,
    text: String,
    icon: Painter = painterResource(R.drawable.loading),
    onClick: () -> Unit,
) {
    MovioButton(
        modifier = modifier
            .padding(horizontal = AppTheme.spacing.medium)
            .fillMaxWidth(),
        color = color,
        onClick = onClick
    ) {
        if (isLoading) {
            MovioIcon(
                painter = icon,
                contentDescription = "loading icon",
                tint = AppTheme.colors.brandColors.onPrimary,
                modifier = Modifier.padding(end = AppTheme.spacing.extraSmall)
            )
        }
        MovioText(
            modifier = Modifier.padding(vertical = AppTheme.spacing.medium),
            text = text,
            color = AppTheme.colors.brandColors.onPrimary,
            textStyle = AppTheme.textStyle.label.medium14,
        )
    }
}
