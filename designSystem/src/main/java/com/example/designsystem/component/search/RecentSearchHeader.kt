package com.example.designsystem.component.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource
import com.example.designsystem.R

@Composable
fun RecentSearchHeader(
    modifier: Modifier = Modifier,
    onClearAll: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(19.dp)
            .width(360.dp),
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
                .height(17.dp)
                .width(54.dp)
        )
    }
}