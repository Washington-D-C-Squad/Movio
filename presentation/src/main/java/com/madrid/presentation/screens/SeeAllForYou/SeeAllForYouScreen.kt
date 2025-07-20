package com.madrid.presentation.screens.SeeAllForYou

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.madrid.design_system.AppTheme
import com.madrid.design_system.component.MovioIcon
import com.madrid.design_system.component.MovioText
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SeeAllForYouScreen(
    viewModel: SeeAllForYouViewModel = koinViewModel(),
) {
    val navController = LocalNavController.current
    val uiState by viewModel.state.collectAsState()

    SeeAllForYouScreenContent(
        onClickBackIcon = {
            navController.popBackStack()
        },
        exploreMoreMovies = uiState.forYouMovies.collectAsLazyPagingItems(),
    )
}

@Composable
private fun SeeAllForYouScreenContent(
    exploreMoreMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    onClickBackIcon: () -> Unit,
    onExploreClick: (LazyPagingItems<SearchScreenState.MovieUiState>) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioIcon(
                    modifier = Modifier.clickable { onClickBackIcon() },
                    painter = painterResource(com.madrid.design_system.R.drawable.arrow_left),
                    contentDescription = null,
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
                Spacer(Modifier.width(8.dp))
                MovioText(
                    text = "For you",
                    color = AppTheme.colors.surfaceColor.onSurface,
                    textStyle = AppTheme.textStyle.headLine.largeBold16,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

            }
            Spacer(Modifier.height(16.dp))

        }
        items(
            count = exploreMoreMovies.itemCount,
        ) { index ->
            MovioVerticalCard(
                modifier = Modifier.fillMaxWidth(fraction = 0.50f),
                description = exploreMoreMovies[index]!!.title,
                movieImage = exploreMoreMovies[index]!!.imageUrl,
                rate = exploreMoreMovies[index]!!.rating,
                width = 500.dp,
                height = 233.dp,
                onClick = { onExploreClick(exploreMoreMovies) }
            )
        }
    }
}