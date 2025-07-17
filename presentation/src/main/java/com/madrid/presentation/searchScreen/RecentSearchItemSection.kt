package com.madrid.presentation.searchScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
import kotlin.text.isNotBlank
import kotlin.text.startsWith
import kotlin.text.substring
import com.madrid.presentation.searchScreen.HighlightedText

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
            HighlightedText(
                text = text,
                searchQuery = searchQuery
            )
        }
        MovioIcon(
            painter = painterResource(id = R.drawable.outline_add),
            contentDescription = stringResource(id = com.madrid.presentation.R.string.remove),
            tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier
                .size(24.dp)
                .clickable { onRemove() }
        )
    }
}