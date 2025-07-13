package com.example.designsystem.component.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecentSearchList(
    items: List<String>,
    modifier: Modifier = Modifier,
    onRemove: (Int) -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            RecentSearchItem(
                text = item,
                onRemove = { onRemove(index) },
                onClick = { onItemClick(index) }
            )
        }
    }
}