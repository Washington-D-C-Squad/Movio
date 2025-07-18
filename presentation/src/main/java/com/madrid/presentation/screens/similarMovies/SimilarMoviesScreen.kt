package com.madrid.presentation.screens.similarMovies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioText
import com.madrid.designsystem.component.TopAppBar
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SimilarMoviesScreen(
    onBackClick: () -> Unit = {},
    onMovieClick: (SearchScreenState.MovieUiState) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var selectedMovieId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
            .statusBarsPadding()
    ) {
        Box {
            TopAppBar(
                text = stringResource(id = com.madrid.presentation.R.string.selected_similar_movie),
                firstIcon = R.drawable.arrow_left
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .size(40.dp)
                    .clickable { onBackClick() }
            )
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(state.searchUiState.exploreMoreMovies) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rating,
                    width = 101.33.dp,
                    height = 136.dp,
                    onClick = {
                        selectedMovieId = movie.id
                        onMovieClick(movie)
                    },
                    modifier = Modifier,
                    paddingvalue = 0.dp
                )
            }
        }
        if (state.searchUiState.isLoading) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                MovioText(
                    text = stringResource(id = com.madrid.presentation.R.string.loading),
                    color = AppTheme.colors.surfaceColor.onSurface,
                    textStyle = AppTheme.textStyle.body.medium14
                )
            }
        }
        state.searchUiState.errorMessage?.let { errorMsg ->
            Box(Modifier
                .fillMaxWidth()
                .padding(16.dp), contentAlignment = Alignment.Center) {
                MovioText(
                    text = errorMsg,
                    color = AppTheme.colors.surfaceColor.outline,
                    textStyle = AppTheme.textStyle.body.medium14
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SimilarMoviesScreenPreview() {
    SimilarMoviesScreen(
        onBackClick = {},
        onMovieClick = {}
    )
} 