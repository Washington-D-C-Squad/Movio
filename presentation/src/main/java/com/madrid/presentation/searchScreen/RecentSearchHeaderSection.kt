package com.madrid.presentation.searchScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.draw.clip
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.R

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
            textStyle = AppTheme.textStyle.title.medium16,
            color = AppTheme.colors.surfaceColor.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        MovioText(
            text = stringResource(id = R.string.clear_all),
            textStyle = AppTheme.textStyle.label.smallRegular14,
            color = AppTheme.colors.surfaceColor.onSurfaceVariant,
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.radius.medium))
                .clickable { onClearAll() }
        )
    }
}