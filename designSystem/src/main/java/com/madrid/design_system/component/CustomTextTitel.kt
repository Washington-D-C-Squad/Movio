package com.example.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.madrid.design_system.AppTheme
import com.madrid.design_system.component.MovioIcon
import com.madrid.design_system.component.MovioText
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
            .padding( vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) { MovioText(
            text = primaryText,
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.title.medium16,
            modifier = Modifier.weight(1f)
        )
        if (secondaryText != null || endIcon != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onSeeAllClick?.invoke() }
            ) {
                secondaryText?.let {
                    MovioText(
                        text = it,
                        color = AppTheme.colors.surfaceColor.onSurfaceVariant,
                        textStyle = AppTheme.textStyle.title.medium14,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                endIcon?.let {
                    MovioIcon(
                        painter = it,
                        contentDescription = "See all",
                        tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}


