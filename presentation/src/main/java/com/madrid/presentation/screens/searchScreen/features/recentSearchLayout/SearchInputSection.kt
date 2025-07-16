package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.textInputField.BasicTextInputField

@Composable
fun SearchInputSection(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchBarClick: () -> Unit
) {
    BasicTextInputField(
        value = searchQuery,
        onValueChange = { newText ->
            onSearchQueryChange(newText)
        },
        hintText = "Search...",
        startIconPainter = painterResource(R.drawable.search_normal),
        endIconPainter = painterResource(R.drawable.outline_add),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSearchBarClick() }
            .padding(top = AppTheme.spacing.medium)
    )
}

