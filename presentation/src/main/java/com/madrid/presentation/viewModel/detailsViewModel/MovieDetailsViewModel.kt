package com.madrid.presentation.viewModel.detailsViewModel

import com.madrid.domain.usecase.mediaDeatailsUseCase.ArtistDetailsUseCase
import com.madrid.domain.usecase.mediaDeatailsUseCase.MovieDetailsUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.viewModel.base.BaseViewModel

class MovieDetailsViewModel(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val actorDetailsUseCase:ArtistDetailsUseCase,
    override val recentSearchUseCase: RecentSearchUseCase,
) : BaseViewModel<MovieDetailsUiState>(MovieDetailsUiState()) {

    fun loadCast(movieId: String) {
        tryToExecute(
            function = { movieDetailsUseCase.getMovieDetailsById(movieId.toInt()) },
            onSuccess = { castList ->
                val mappedCast = castList.map { castMember ->
                    MovieDetailsUiState.CastUiState(
                        actorImageUrl = castMember.imageUrl,
                        actorName = castMember.name,
                        actorRole = castMember.role,
                        dateOfBirth = castMember.dateOfBirth,
                        location = castMember.location,
                        id = castMember.id,
                        description = castMember.description,
                        knownFor = castMember.knownFor.map { known ->
                            MovieDetailsUiState.KnownMovieUiState(
                                title = known.title,
                                imageUrl = known.imageUrl,
                                rating = known.rating
                            )
                        }
                    )
                }
                updateState { it.copy(cast = mappedCast, isLoading = false, errorMessage = null) }
                        },
            onError = { error ->
                updateState { it.copy(isLoading = false, errorMessage = error.message ?: "Unknown error") }
            }
        )
    }

    fun loadActorDetails(actorId: String) {
        tryToExecute(
            function = { actorDetailsUseCase.getArtistDetailsById(actorId.toInt()) },
            onSuccess = { actor ->
                val mappedActor = actor?.let {
                    MovieDetailsUiState.CastUiState(
                        actorImageUrl = it.imageUrl,
                        actorName = it.name,
                        actorRole = it.role,
                        dateOfBirth = it.dateOfBirth,
                        location = it.name,
                        id = it.id.toString(),
                        description = it.description,
                        knownFor = it.artisKnownFor!!.map { known ->
                            MovieDetailsUiState.KnownMovieUiState(
                                title = known.title,
                                imageUrl = known.imageUrl,
                                rating ="5.0",
                            )
                        }
                    )
                }
                updateState { it.copy(selectedActor = mappedActor, isLoading = false, errorMessage = null) }
            },
            onError = { error ->
                updateState { it.copy(isLoading = false, errorMessage = error.message ?: "Unknown error") }
            }
        )
    }
}
