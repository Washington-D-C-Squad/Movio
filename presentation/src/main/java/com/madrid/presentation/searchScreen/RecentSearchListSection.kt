package com.madrid.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.MovioTextHighlight

@Composable
fun RecentSearchList(
    items: List<String>,
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onRemove: (String) -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    val filteredItems by remember(searchQuery, items) {
        derivedStateOf {
            if (searchQuery.isEmpty()) items
            else items.filter { it.contains(searchQuery, ignoreCase = true) }
        }
    }
    Column(modifier = modifier) {
        if (filteredItems.isEmpty()) {
            // No results message removed: show nothing
        } else {
            filteredItems.forEach { item ->
                RecentSearchItem(
                    text = item,
                    searchQuery = searchQuery,
                    onRemove = { onRemove(item) },
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun HighlightedText(
    text: String,
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    val highlightedTextColor = AppTheme.colors.surfaceColor.onSurfaceVariant
    val normalTextColor = AppTheme.colors.surfaceColor.onSurface_3
    if (searchQuery.isEmpty()) {
        MovioText(
            text = text,
            textStyle = AppTheme.textStyle.label.smallRegular14,
            color = normalTextColor,
            modifier = modifier
        )
    } else {
        val startIndex = text.indexOf(searchQuery, ignoreCase = true)
        if (startIndex == -1) {
            MovioText(
                text = text,
                textStyle = AppTheme.textStyle.label.smallRegular14,
                color = normalTextColor,
                modifier = modifier
            )
        } else {
            val endIndex = startIndex + searchQuery.length
            val annotatedString = buildAnnotatedString {
                append(text.substring(0, startIndex))
                withStyle(SpanStyle(color = highlightedTextColor)) {
                    append(text.substring(startIndex, endIndex))
                }
                append(text.substring(endIndex))
            }
            MovioTextHighlight(
                text = annotatedString,
                textStyle = AppTheme.textStyle.label.smallRegular14,
                color = normalTextColor,
                modifier = modifier
            )
        }
    }
}