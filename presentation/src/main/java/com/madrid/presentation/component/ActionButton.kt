package com.madrid.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.madrid.designsystem.MovioTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioButton
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    text: String,
    onClick: () -> Unit,
    color: Color = MovioTheme.colors.brandColors.primary,
    icon: Painter = painterResource(R.drawable.loading),
) {
    MovioButton(
        modifier = modifier
            .padding(horizontal = MovioTheme.spacing.medium)
            .fillMaxWidth(),
        color = color,
        onClick = onClick
    ) {
        if (isLoading) {
            MovioIcon(
                painter = icon,
                contentDescription = "loading icon",
                tint = MovioTheme.colors.brandColors.onPrimary,
                modifier = Modifier.padding(end = MovioTheme.spacing.extraSmall)
            )
        }
        MovioText(
            modifier = Modifier.padding(vertical = MovioTheme.spacing.medium),
            text = text,
            color = MovioTheme.colors.brandColors.onPrimary,
            textStyle = MovioTheme.textStyle.label.medium14,
        )
    }
}
