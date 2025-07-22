package com.madrid.presentation.screens.detailsScreen.seriesDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.presentation.component.MovieDetailsNavigationHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.navigation.Destinations
import com.madrid.designSystem.component.TopAppBar
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.domain.entity.Episode
import com.madrid.presentation.component.CustomDropdown
import com.madrid.presentation.component.movioCards.MovioEpisodesCard

@Composable
fun EpisodesScreen() {
    EpisodesScreenContent()
}

@Composable
fun EpisodesScreenContent() {
    val imageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    val episodes: List<Episode> = getFakeEpisodes()
    Column {
        Box() {
            MoviePosterDetailScreen(
                imageUrl = imageUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Theme.color.surfaces.surface)
            )
            TopAppBar(
                text = null,
                secondIcon = null,
                thirdIcon = null,
                modifier = Modifier.padding(start = 16.dp, top = 36.dp),
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 412.dp)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 40.dp)
            ) {
                items(episodes) { episode ->
                    MovioEpisodesCard(
                        modifier = Modifier.padding(vertical = 8.dp),
                        movieTitle = episode.title,
                        movieRate = episode.rate.toString(),
                        currentMovieEpisode = "Episode ${episode.episodeNumber.toString().padStart(2, '0')}",
                        movieTime = episode.duration,
                        movieImageUrl = episode.imageUrl,
                        onClick = {
                        },
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 370.dp)
                    .padding(horizontal = 16.dp)
            ) {
                MovioText(
                    text = "Episode 25",
                    textStyle = Theme.textStyle.headline.mediumMedium18,
                    color = Theme.color.surfaces.onSurface
                )
                Spacer(Modifier.weight(1f))
                var selectedItem by remember { mutableStateOf("Season 1") }
                CustomDropdown(
                    items = listOf("Season 1", "Season 2", "Season 3"),
                    selectedItem = selectedItem,
                    labelSelector = { it },
                    onItemSelected = { selectedItem = it }
                )
            }


        }

    }

}

private fun getFakeEpisodes(): List<Episode>{
    val imageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    return listOf(
        Episode(
            id = 81,
            title = "spider man",
            episodeNumber = 1,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 7.5
        ),
        Episode(
            id = 81,
            title = "non man",
            episodeNumber = 2,
            duration = "40 m",
            imageUrl = imageUrl,
            rate = 5.0
        ),
        Episode(
            id = 81,
            title = "lol man",
            episodeNumber = 3,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 9.0
        ),
        Episode(
            id = 81,
            title = "spider man",
            episodeNumber = 1,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 7.5
        ),
        Episode(
            id = 81,
            title = "non man",
            episodeNumber = 2,
            duration = "40 m",
            imageUrl = imageUrl,
            rate = 5.0
        ),
        Episode(
            id = 81,
            title = "lol man",
            episodeNumber = 3,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 9.0
        ),
        Episode(
            id = 81,
            title = "spider man",
            episodeNumber = 1,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 7.5
        ),
        Episode(
            id = 81,
            title = "non man",
            episodeNumber = 2,
            duration = "40 m",
            imageUrl = imageUrl,
            rate = 5.0
        ),
        Episode(
            id = 81,
            title = "lol man",
            episodeNumber = 3,
            duration = "50 m",
            imageUrl = imageUrl,
            rate = 9.0
        ),
    )
}

@Composable
private fun PreviewEpisodesScreen() {
    EpisodesScreen()
}
