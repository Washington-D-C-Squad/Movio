package com.madrid.presentation.screens.searchScreen

import HeaderSectionBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.textInputField.BasicTextInputField
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilteredScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val uiState by viewModel.uiState.collectAsState()

        ContentFilteredScreen()

        uiState.errorMessage?.let { errorMsg ->
            LaunchedEffect(errorMsg) {
                // handle error
            }


            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    MovioIcon(
                        painter = painterResource(R.drawable.loading),
                        contentDescription = "Loading",
                        tint = AppTheme.colors.brandColors.primary
                    )
                }
            }

        }
    }
}

@Composable
private fun ContentFilteredScreen(

) {
    BasicTextInputField(
        value = "",
        onValueChange = {},
        hintText = "search ...",
        startIconPainter = painterResource(R.drawable.search_normal),
        endIconPainter = painterResource(R.drawable.outline_add),
        onClickEndIcon = {},
    )
    HeaderSectionBar(
        tabs = listOf(tabs.Top_Result.name,tabs.Movies.name,tabs.Series.name,tabs.Artists.name),
        selectedTabIndex = 1,
        onTabSelected = {},
    )

}







enum class tabs{
    Top_Result,
    Movies,
    Series,
    Artists
}