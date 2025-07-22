package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.R
import com.madrid.presentation.component.RecentSearchItem


fun LazyGridScope.recentSearchScreen(
    searchHistory: List<String>,
    searchQuery: String,
    onSearchItemClick: (String) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearAll: () -> Unit,
    highlightCharactersInText: (String, String, Color, Color, TextStyle) -> AnnotatedString,
    modifier: Modifier = Modifier
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = stringResource(id = R.string.recent_search),
                textStyle = Theme.textStyle.title.mediumMedium16,
                color = Theme.color.surfaces.onSurface
            )
            MovioText(
                text = stringResource(id = R.string.clear_all),
                textStyle = Theme.textStyle.label.smallRegular14,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.clickable { onClearAll() }
            )
        }
    }
    items(
       count =  searchHistory.size,
        span = { GridItemSpan(maxLineSpan) }
    ) { searchItem ->
        RecentSearchItem(
            searchText = searchHistory[searchItem],
            searchQuery = searchQuery,
            onItemClick = { onSearchItemClick(searchHistory[searchItem]) },
            onRemoveClick = { onRemoveItem(searchHistory[searchItem]) },
            highlightCharactersInText = highlightCharactersInText,
        )
    }
}