package com.madrid.presentation.screens.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.AppTheme
import com.madrid.designSystem.component.TopAppBar
import com.madrid.presentation.R
import com.madrid.presentation.composables.movioCards.MovioArtistsCard
import com.madrid.presentation.screens.searchScreen.viewModel.MovieDetailsUiState
import com.madrid.presentation.screens.searchScreen.viewModel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopCastDetailsScreen(
    movieId:String,
    viewModel: MovieDetailsViewModel = koinViewModel(),
) {
    val uiState by viewModel.state.collectAsState()
    TopCastDetailsContent(artist = uiState.cast)

    LaunchedEffect(Unit) {
        viewModel.loadCast(movieId)
    }
}

@Composable
fun TopCastDetailsContent(
    artist: List<MovieDetailsUiState.CastUiState>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 101.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TopAppBar(stringResource(R.string.top_cast), secondIcon = null, thirdIcon = null)
        }
        items(
            count = artist.size,
        ) { index ->
            MovioArtistsCard(
                artistsName = artist[index].name,
                imageUrl = artist[index].imageUrl,
                width = 101.dp,
                onClick = { }
            )
        }
    }
}