package com.madrid.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.AppTheme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    text: String,
    onClick: () -> Unit,
    color: Color = AppTheme.colors.brandColors.primary,
    icon: Painter = painterResource(R.drawable.loading),
) {
    MovioButton(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        color = color,
        onClick = onClick
    ) {
        if (isLoading) {
            MovioIcon(
                painter = icon,
                contentDescription = "loading icon",
                tint = AppTheme.colors.brandColors.onPrimary,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
        MovioText(
            modifier = Modifier.padding(vertical = 16.dp),
            text = text,
            color = AppTheme.colors.brandColors.onPrimary,
            textStyle = AppTheme.textStyle.label.medium14,
        )
    }
}
