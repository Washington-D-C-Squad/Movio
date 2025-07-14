package com.example.presentation.component.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun RecentSearchItem(
    text: String,
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onRemove: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioIcon(
            painter = painterResource(id = R.drawable.outline_history),
            contentDescription = "History Icon",
            tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (searchQuery.isNotBlank() && text.startsWith(searchQuery, ignoreCase = true)) {
                MovioText(
                    annotatedString = buildAnnotatedString {
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = AppTheme.colors.surfaceColor.onSurface)) {
                            append(text.substring(0, searchQuery.length))
                        }
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = AppTheme.colors.surfaceColor.onSurfaceVariant)) {
                            append(text.substring(searchQuery.length))
                        }
                    },
                    textStyle = AppTheme.textStyle.label.smallRegular14,
                    color = AppTheme.colors.surfaceColor.onSurface
                )
            } else {
                MovioText(
                    text = text,
                    textStyle = AppTheme.textStyle.label.smallRegular14,
                    color = AppTheme.colors.surfaceColor.onSurface
                )
            }
        }
        MovioIcon(
            painter = painterResource(id = R.drawable.outline_add),
            contentDescription = stringResource(id = R.string.remove_icon),
            tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier
                .size(24.dp)
                .clickable { onRemove() }
        )
    }
}