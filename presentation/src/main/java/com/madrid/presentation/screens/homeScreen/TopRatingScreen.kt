package com.madrid.presentation.screens.homeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.FilterBar
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.R
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.viewModel.homeScreenViewModel.TopScreenViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopRatingScreen(
    viewModel: TopScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val items = uiState.genreMovie
    Log.d("log items", "TopRatingScreen: $items")
    Log.d("log items", "GenreMovie: ${uiState.genreMovie}")
    Log.d("log items", "Filtered: ${uiState.filteredMovies}")

    var selectedItem by remember { mutableStateOf("") }

    Log.d("log items", "TopRatingScreen: $selectedItem")

    LaunchedEffect(Unit) {
        if (uiState.genreMovie.isNotEmpty()) {
            val firstGenre = uiState.genreMovie.first()
            selectedItem = firstGenre
            viewModel.onGenreSelected(firstGenre)
        }
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TopAppBar("Top Rating", secondIcon = null, thirdIcon = null)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            FilterBar(
                items = items,
                selectedItem = selectedItem,
                onItemClick = { genre ->
                    selectedItem = genre
                    viewModel.onGenreSelected(genre)
                },
                scrollable = true
            )
        }

        items(uiState.filteredMovies.size) { index ->
            val movie = uiState.filteredMovies[index]
            MovioVerticalCard(
                description = movie.name,
                movieImage = movie.imageUrl,
                rate = movie.rate.toString(),
                width = 101.dp,
                height = 136.dp,
                onClick = { /* handle click */ }
            )
        }
    }
}


@Preview
@Composable
private fun TopRatingScreenPreview(modifier: Modifier = Modifier) {
    TopRatingScreen()
}