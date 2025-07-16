package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.composables.RecentSearchItem


fun LazyGridScope.recentSearchScreen(
    searchHistory: List<String>,
    onSearchItemClick: (String) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.spacing.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "recent search ", // stringResource(id = DesignSystemR.string.recent_search),
                textStyle = AppTheme.textStyle.headLine.medium18,
                color = AppTheme.colors.surfaceColor.onSurface
            )
            MovioText(
                text = "clear all ", // stringResource(id = DesignSystemR.string.clear_all),
                textStyle = AppTheme.textStyle.body.medium14,
                color = AppTheme.colors.surfaceColor.onSurfaceVariant,
                modifier = Modifier.clickable { onClearAll() }
            )
        }
    }
    items(searchHistory.size) { searchItem ->
        RecentSearchItem(
            searchText = searchHistory[searchItem],
            onItemClick = { onSearchItemClick(searchHistory[searchItem]) },
            onRemoveClick = { onRemoveItem(searchHistory[searchItem]) }
        )
    }
}