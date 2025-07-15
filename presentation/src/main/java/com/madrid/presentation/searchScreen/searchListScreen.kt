package com.madrid.presentation.searchScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R as DesignSystemR
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.R
import com.madrid.presentation.component.screens.searchScreen.SearchViewModel

@Composable
fun RecentSearchScreen(viewModel: SearchViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
            .padding(vertical = 32.dp)
    ) {
        BasicTextInputField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            hintText = stringResource(id = R.string.search),
            startIconPainter = painterResource(id = DesignSystemR.drawable.search_normal),
            endIconPainter = painterResource(id = DesignSystemR.drawable.outline_add),
            onClickEndIcon = { viewModel.onSearchQueryChange("") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            borderBrushColors = listOf(
                AppTheme.colors.surfaceColor.onSurfaceContainer,
                AppTheme.colors.surfaceColor.onSurfaceContainer
            ),
            iconColorInFocus = AppTheme.colors.surfaceColor.onSurfaceVariant,
            iconColorNotFocus = AppTheme.colors.surfaceColor.onSurfaceVariant,
            cursorColor = AppTheme.colors.surfaceColor.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        RecentSearchHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClearAll = { viewModel.clearRecentSearchesWithWorker(context) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchList(
            items = uiState.recentSearches,
            searchQuery = searchQuery,
            onRemove = viewModel::removeRecentSearch,
            onItemClick = viewModel::onRecentSearchItemClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}