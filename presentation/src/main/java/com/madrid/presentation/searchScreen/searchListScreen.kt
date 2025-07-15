package com.madrid.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.viewModel.base.SearchViewModel
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R as DesignSystemR
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecentSearchScreen(viewModel: SearchViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
            .padding(vertical = 32.dp)
    ) {
        BasicTextInputField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            hintText = stringResource(id = R.string.search),
            startIconPainter = painterResource(id = DesignSystemR.drawable.search_normal),
            endIconPainter = painterResource(id = DesignSystemR.drawable.outline_add),
            onClickEndIcon = {
                searchQuery = ""
            },
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
            onClearAll = { viewModel.clearAll() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchList(
            items = state.recentSearchUiState,
            searchQuery = searchQuery,
            onRemove = { index ->
                val item = state.recentSearchUiState.getOrNull(index)
                if (item != null) {
                    viewModel.removeRecentSearch(item)
                    viewModel.loadRecentSearches()
                }
            },
            onItemClick = { index ->
                val item = state.recentSearchUiState.getOrNull(index)
                if (item != null) {
                    searchQuery = item
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}