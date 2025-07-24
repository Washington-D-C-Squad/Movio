package com.madrid.presentation.screens.detailsScreen.castDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.R
import com.madrid.presentation.component.movioCards.MovioArtistsCard
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsUiState
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopCastScreenList(
    viewModel: MovieDetailsViewModel = koinViewModel(),

) {

    val movieId = String()
    val onArtistClick: (String) -> Unit = {}
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadCast(movieId)
    }

    TopCastContent(
        cast = uiState.cast,
        onArtistClick = onArtistClick
    )
}

@Composable
fun TopCastContent(
    cast: List<MovieDetailsUiState.CastUiState>,
    onArtistClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (cast.isEmpty()) {
            EmptyCastMessage()
        } else {
            CastGrid(cast = cast, onArtistClick = onArtistClick)
        }
    }
}

@Composable
private fun CastGrid(
    cast: List<MovieDetailsUiState.CastUiState>,
    onArtistClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 101.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            TopAppBar(
                null
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(cast) { artist ->
            MovioArtistsCard(
                imageUrl = artist.imageUrl,
                artistsName = artist.name,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                onClick = { onArtistClick(artist.name) }
            )
        }
    }
}

@Composable
private fun EmptyCastMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        MovioText(
            text = stringResource(R.string.no_cast_available),
            color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.body.mediumMedium14
        )
    }
}

@Preview
@Composable
private fun TopCastScreenPreview() {
    MovioTheme {
        TopCastContent(
            cast = listOf(
                MovieDetailsUiState.CastUiState(
                    name = "Actor Name",
                    imageUrl = ""
                )
            )
        )
    }
}