package com.madrid.presentation.screens.homeScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.CustomTextTitel
import com.madrid.presentation.component.movioCards.MovioVerticalCard


fun <T> LazyGridScope.GenericMediaRow(
    items: List<T>,
    onItemClick: (T) -> Unit = {},
    onClickSeeAll: () -> Unit,
    sectionTitle: String,
    mapper: (T) -> DisplayableMediaItem
) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        CustomTextTitel(primaryText = sectionTitle)
    }

    item(span = { GridItemSpan(maxLineSpan) }) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(234.dp)
        ) {
            items(items) { item ->
                val display = mapper(item)
                MovioVerticalCard(
                    description = display.title,
                    movieImage = display.imageUrl,
                    rate = display.rating,
                    width = 158.dp,
                    height = 180.dp,
                    paddingValue = 8.dp,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

data class DisplayableMediaItem(
    val title: String,
    val imageUrl: String,
    val rating: String
)

