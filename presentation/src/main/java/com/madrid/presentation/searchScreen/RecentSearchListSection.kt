package com.madrid.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.collections.forEachIndexed

@Composable
fun RecentSearchList(
    items: List<String>,
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    onRemove: (String) -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            RecentSearchItem(
                text = item,
                searchQuery = searchQuery,
                onRemove = { onRemove(item) },
                onClick = { onItemClick(item) }
            )
        }
    }
}