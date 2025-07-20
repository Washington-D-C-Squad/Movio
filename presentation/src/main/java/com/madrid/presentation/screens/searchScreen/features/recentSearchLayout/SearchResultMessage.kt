package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.R

@Composable
fun SearchResultMessage(items: String, modifier: Modifier = Modifier) {
    MovioText(
        stringResource(id = R.string.search_result_count, items),
        AppTheme.colors.surfaceColor.onSurfaceVariant,
        AppTheme.textStyle.label.smallRegular14,
        modifier = modifier
    )
}