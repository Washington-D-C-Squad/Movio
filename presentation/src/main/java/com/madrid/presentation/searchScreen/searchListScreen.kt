package com.madrid.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R as DesignSystemR
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import com.madrid.presentation.R
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel

@Composable
fun RecentSearchScreen(viewModel: SearchViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

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
            onClickEndIcon = {
                viewModel.onSearchQueryChange("")
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
            cursorColor = AppTheme.colors.surfaceColor.onSurface,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.onSearchSubmit()
                    keyboardController?.hide()
                }
            )
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
            onRemove = { item ->
                viewModel.removeRecentSearch(item)
            },
            onItemClick = { item ->
                viewModel.onSearchQueryChange(item)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}