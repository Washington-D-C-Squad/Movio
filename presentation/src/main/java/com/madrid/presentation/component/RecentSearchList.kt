package com.madrid.presentation.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.R
import com.madrid.designSystem.R as DesignSystemR

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
            .padding(horizontal = 20.dp)
            .background(
                Theme.color.surfaces.surface,
                RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioText(
                    text = stringResource(id = R.string.recent_search),
                    textStyle = Theme.textStyle.headline.mediumMedium18,
                    color = Theme.color.surfaces.onSurface
                )
                MovioText(
                    text = stringResource(id = R.string.clear_all),
                    textStyle = Theme.textStyle.body.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceVariant,
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
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MovioIcon(
            painter = painterResource(id = DesignSystemR.drawable.outline_clock_circle),
            contentDescription = stringResource(id = R.string.clock_content_description),
            tint = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        MovioText(
            text = searchText,
            textStyle = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )

        MovioButton(
            onClick = onRemoveClick,
            color = Color.Transparent
        ) {
            MovioIcon(
                painter = painterResource(id = DesignSystemR.drawable.outline_add),
                contentDescription = stringResource(id = R.string.delete_content_description),
                tint = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun RecentSearchHeader(
    modifier: Modifier = Modifier,
    onClearAll: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(19.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = stringResource(id = R.string.recent_search),
            textStyle = Theme.textStyle.title.mediumMedium16,
            color = Theme.color.surfaces.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        MovioText(
            text = stringResource(id = R.string.clear_all),
            textStyle = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.Companion
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClearAll() }
        )
    }
}