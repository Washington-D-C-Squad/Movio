package com.example.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.example.designsystem.AppTheme


@Composable
fun CustomTextTitel(
    primaryText: String,
    modifier: Modifier = Modifier,
    secondaryText: String? = null,
    endIcon: Painter? = null,
    onSeeAllClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.medium, vertical = AppTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) { MovioText(
            text = primaryText,
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.label.largeMedium16,
            modifier = Modifier.weight(1f)
        )
        if (secondaryText != null || endIcon != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                secondaryText?.let {
                    MovioText(
                        text = it,
                        color = AppTheme.colors.surfaceColor.onSurface_3,
                        textStyle = AppTheme.textStyle.label.medium14,
                        modifier = Modifier.padding(end = AppTheme.spacing.small)
                    )
                }
                endIcon?.let {
                    MovioIcon(
                        painter = it,
                        contentDescription = "See all",
                        tint = AppTheme.colors.surfaceColor.onSurface_3,
                        modifier = Modifier.clickable { onSeeAllClick?.invoke() }
                    )
                }
            }
        }
    }
}


