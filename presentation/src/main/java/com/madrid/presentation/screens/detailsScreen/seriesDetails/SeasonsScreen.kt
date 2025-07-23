package com.madrid.presentation.screens.detailsScreen.seriesDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.TopAppBar
import com.madrid.presentation.component.movioCards.MovioSeasonCard
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.viewModel.detailsViewModel.SeriesDetailsUiState
import com.madrid.presentation.viewModel.detailsViewModel.SeriesDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SeasonsScreen(viewModel: SeriesDetailsViewModel = koinViewModel()) {
    val uiState by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    SeasonsScreenContent(
        uiState = uiState,
        onClickBack = { navController.navigate(Destinations.SearchScreen) },
        onClickSeason = { seasonNumber ->
            navController.navigate(
                Destinations.EpisodesScreen(
                    seriesId = uiState.seriesId,
                    seasonNumber = seasonNumber
                )
            )
        },
    )
}

@Composable
fun SeasonsScreenContent(
    uiState: SeriesDetailsUiState,
    onClickBack: () -> Unit = {},
    onClickSeason: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        TopAppBar(
            text = "Current Seasons",
            secondIcon = null,
            thirdIcon = null,
            onFirstIconClick = { onClickBack() },
            modifier = Modifier.padding(top = 36.dp),
        )
        Spacer(Modifier.height(20.dp))
        val seasons = uiState.currentSeasonsUiStates
        LazyColumn(
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            itemsIndexed(seasons) { index, season ->
                MovioSeasonCard(
                    movieTitle = season.seasonNumber.toString(),
                    movieImage = season.imageUrl,
                    movieRate = season.rate,
                    totalNumberOfEpisodes = season.numberOfEpisodes.toString(),
                    onClick = { onClickSeason(season.seasonNumber) },
                    yearOfPublish = season.productionDate,
                    currentSeason = (index + 1).toString(),
                    timeOfPublish = season.productionDate,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }

}

data class testS(
    val movieTitle: String = "Spider-Man: Homecoming",
    val movieImage: String = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
    val movieRate: String = " 3.0",
    val totalNumberOfEpisodes: String = "1",
    val onClick: () -> Unit = {},
    val yearOfPublish: String = "2004",
    val currentSeason: String = "7",
    val timeOfPublish: String = "october 4 , 2002"
)
