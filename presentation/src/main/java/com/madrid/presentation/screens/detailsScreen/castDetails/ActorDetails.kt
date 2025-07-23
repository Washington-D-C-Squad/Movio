package com.madrid.presentation.screens.detailsScreen.castDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.component.TextWithReadMore
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.header.ActorDetailsHeader
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.viewModel.detailsViewModel.ActorDetailsViewModel
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsUiState
import com.madrid.presentation.viewModel.detailsViewModel.TopCastViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ActorDetails(viewModel: ActorDetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()

    uiState.selectedActor?.let { actor ->
        ActorDetailsContent(actor)
    }
}

@Composable
fun ActorDetailsContent(
    actor: MovieDetailsUiState.CastUiState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Box(contentAlignment = Alignment.Center) {
                com.madrid.designSystem.component.TopAppBar(
                    null,
                    secondIcon = null,
                    thirdIcon = null,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .align(Alignment.TopCenter)
                )
                MoviePosterDetailScreen(
                    imageUrl = actor.actorImageUrl,
                    isActor = true
                )
            }
        }

        item {
            ActorDetailsHeader(
                actorName = actor.actorName,
                actorRole = actor.actorRole,
                dateOfBirth = actor.dateOfBirth,
                location = actor.location,
            )
        }

        item {
            TextWithReadMore(
                description = actor.description,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                maxLines = 5
            )
        }

        item {
            MovioText(
                text = "Known For",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.title.mediumMedium14,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(333.dp),
            ) {
                items(actor.knownFor.size) { index ->
                    val movie = actor.knownFor[index]
                    MovioVerticalCard(
                        description = movie.title,
                        movieImage = movie.imageUrl,
                        rate = movie.rating,
                        width = 100.dp,
                        height = 178.dp,
                        onClick = { /* Handle movie click */ },
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}