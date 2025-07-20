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
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.screens.searchScreen.SeeAllForYou.SeeAllForYouViewModel
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SeeAllForYouScreen(
    viewModel: SeeAllForYouViewModel = koinViewModel(),
) {
    val navController = LocalNavController.current
    val uiState by viewModel.state.collectAsState()

    SeeAllForYouScreenContent(
        onClickBackIcon = {
            navController.navigate(Destinations.SearchScreen)
        },
        exploreMoreMovies = uiState.forYouMovies.collectAsLazyPagingItems(),
        onMovieClick = { movieId ->
            navController.navigate(Destinations.MovieDetailsScreen(movieId))
        }
    )
}

@Composable
private fun SeeAllForYouScreenContent(
    exploreMoreMovies: LazyPagingItems<SearchScreenState.MovieUiState>,
    onClickBackIcon: () -> Unit,
    onExploreClick: (LazyPagingItems<SearchScreenState.MovieUiState>) -> Unit = {},
    onMovieClick: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
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
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioIcon(
                    modifier = Modifier.clickable { onClickBackIcon() },
                    painter = painterResource(com.madrid.designSystem.R.drawable.arrow_left),
                    contentDescription = null,
                    tint = Theme.color.surfaces.onSurface
                )
                Spacer(Modifier.width(8.dp))
                MovioText(
                    text = "For you",
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.headline.largeBold16,
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
                height = 178.dp,
                onClick = { onMovieClick(exploreMoreMovies[index]!!.id.toInt())}
            )
        }
    }
}