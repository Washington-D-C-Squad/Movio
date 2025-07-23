package com.madrid.presentation.screens.detailsScreen.castDetails

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
import androidx.navigation.NavHostController
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.TopAppBar
import com.madrid.presentation.R
import com.madrid.presentation.component.movioCards.MovioArtistsCard
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsUiState
import com.madrid.presentation.viewModel.detailsViewModel.TopCastViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopCastDetailsScreen(
    viewModel: TopCastViewModel = koinViewModel(),
) {
    val uiState by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    TopCastDetailsContent(
        artist = uiState.cast,
        onActorClick = { artistId ->
            navController.navigate(Destinations.ActorDetails(artistId))
        }
    )

}

@Composable
fun TopCastDetailsContent(
    artist: List<MovieDetailsUiState.CastUiState>,
    onActorClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 101.dp),
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
            TopAppBar(stringResource(R.string.top_cast), secondIcon = null, thirdIcon = null)

        }
        items(artist.size) { castMember ->
            MovioArtistsCard(
                artistsName = artist[castMember].actorName,
                imageUrl = artist[castMember].actorImageUrl,
                onClick = { onActorClick(artist[castMember].id.toInt()) }
            )
        }
    }
}