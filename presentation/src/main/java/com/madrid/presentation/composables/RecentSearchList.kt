package com.madrid.presentation.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R as DesignSystemR
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioButton

@Composable
fun RecentSearchList(
    modifier: Modifier = Modifier,
    searchHistory: List<String> = emptyList(),
    onSearchItemClick: (String) -> Unit = {},
    onRemoveItem: (String) -> Unit = {},
    onClearAll: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.large)
            .background(
                AppTheme.colors.surfaceColor.surface,
                RoundedCornerShape(AppTheme.radius.large)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.spacing.small)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.spacing.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioText(
                    text = "recent search " , // stringResource(id = DesignSystemR.string.recent_search),
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

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(searchHistory) { searchItem ->
                    RecentSearchItem(
                        searchText = searchItem,
                        onItemClick = { onSearchItemClick(searchItem) },
                        onRemoveClick = { onRemoveItem(searchItem) }
                    )
                }
            }
        }
    }
}

@Composable
fun RecentSearchItem(
    searchText: String,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(horizontal = AppTheme.spacing.large, vertical = AppTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MovioIcon(
            painter = painterResource(id = DesignSystemR.drawable.outline_clock_circle),
            contentDescription = "description " , //stringResource(id = DesignSystemR.string.clock_content_description),
            tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier.size(AppTheme.spacing.medium)
        )

        Spacer(modifier = Modifier.width(AppTheme.spacing.medium))

        MovioText(
            text = searchText,
            textStyle = AppTheme.textStyle.body.medium14,
            color = AppTheme.colors.surfaceColor.onSurface,
            modifier = Modifier.weight(1f)
        )

        MovioButton(
            onClick = onRemoveClick,
            modifier = Modifier.size(AppTheme.spacing.large),
            color = AppTheme.colors.surfaceColor.surface
        ) {
            MovioIcon(
                painter = painterResource(id = DesignSystemR.drawable.trash),
                contentDescription = "delete content description" , //stringResource(id = DesignSystemR.string.delete_content_description),
                tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                modifier = Modifier.size(AppTheme.spacing.medium)
            )
        }
    }
}