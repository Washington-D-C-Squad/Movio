package com.example.designsystem.component.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecentSearchList(
    items: List<String>,
    modifier: Modifier = Modifier,
    onRemove: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            RecentSearchItem(
                text = item,
                onRemove = { onRemove(index) }
            )
        }
    }
}