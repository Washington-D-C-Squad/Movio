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
    onRemove: (Int) -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            RecentSearchItem(
                text = item,
                searchQuery = searchQuery,
                onRemove = { onRemove(index) },
                onClick = { onItemClick(index) }
            )
        }
    }
}