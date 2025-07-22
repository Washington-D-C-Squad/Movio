package com.madrid.presentation.screens.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.movieActorBackground.MoviePosterDetailScreen
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.composables.ActorDetailsHeader
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsUiState
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ActorDetails(actorId: String, viewModel: MovieDetailsViewModel = koinViewModel()) {

    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadActorDetails(actorId)
    }
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
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Box(contentAlignment = Alignment.Center) {
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
                Location = actor.location,
            )
        }

        item {
            MovioText(
                text = actor.description,
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.label.smallRegular14,
                maxLines = 5,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
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

