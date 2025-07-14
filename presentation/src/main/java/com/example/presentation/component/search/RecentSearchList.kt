package com.example.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.component.search.RecentSearchItem

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