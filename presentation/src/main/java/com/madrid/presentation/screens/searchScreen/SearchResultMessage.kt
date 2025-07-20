package com.madrid.presentation.screens.searchScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioText
import com.madrid.presentation.R

@Composable
fun SearchResultMessage(items: String, modifier: Modifier = Modifier) {
    MovioText(
        stringResource(id = R.string.search_result_count, items),
        Theme.color.surfaces.onSurfaceVariant,
        Theme.textStyle.label.smallRegular14,
        modifier = modifier
    )
}