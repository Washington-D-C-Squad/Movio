package com.example.movio.component


import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movio.R
import com.example.designsystem.AppTheme

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
            .background(AppTheme.colors.surfaceColor.surface, RoundedCornerShape(AppTheme.radius.large))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.spacing.small)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.spacing.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Search",
                    style = AppTheme.textStyle.headLine.medium,
                    color = AppTheme.colors.surfaceColor.onSurface
                )
                Text(
                    text = "Clear all",
                    style = AppTheme.textStyle.body.medium14,
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
private fun RecentSearchItem(
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

        Icon(
            painter = painterResource(id = R.drawable.clock),
            contentDescription = "Clock",
            tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(AppTheme.spacing.medium))

        Text(
            text = searchText,
            style = AppTheme.textStyle.body.medium14,
            color = AppTheme.colors.surfaceColor.onSurface,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier.size(AppTheme.spacing.large)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                modifier = Modifier.size(AppTheme.spacing.medium)
            )
        }
    }
}