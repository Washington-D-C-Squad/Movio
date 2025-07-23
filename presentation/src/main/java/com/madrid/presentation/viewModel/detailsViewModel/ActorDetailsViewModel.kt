package com.madrid.presentation.viewModel.detailsViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.madrid.domain.usecase.mediaDeatailsUseCase.ArtistDetailsUseCase
import com.madrid.presentation.navigation.Destinations
import com.madrid.presentation.viewModel.base.BaseViewModel
import com.madrid.presentation.viewModel.effect.effect

class ActorDetailsViewModel(
    private val actorDetailsUseCase: ArtistDetailsUseCase,
    private val saveStateHandle: SavedStateHandle
) : BaseViewModel<MovieDetailsUiState, effect>(MovieDetailsUiState()
) {
    private val args = saveStateHandle.toRoute<Destinations.ActorDetails>()

    init {
        loadActorDetails()
    }

    private fun loadActorDetails() {
        tryToExecute(
            function = { actorDetailsUseCase.getArtistDetailsById(args.artistId) },
            onSuccess = { actor ->
                val mappedActor = actor?.let {
                    MovieDetailsUiState.CastUiState(
                        actorImageUrl = it.imageUrl,
                        actorName = it.name,
                        actorRole = it.role,
                        dateOfBirth = it.dateOfBirth,
                        location = it.country,
                        id = it.id.toString(),
                        description = it.description,
                        knownFor = it.artisKnownFor!!.map { known ->
                            MovieDetailsUiState.KnownMovieUiState(
                                title = known.title,
                                imageUrl = known.imageUrl,
                                rating = known.voteAverage.toString(),
                            )
                        }
                    )
                }
                updateState {
                    it.copy(
                        selectedActor = mappedActor,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Unknown error"
                    )
                }
            }
        )
    }
}