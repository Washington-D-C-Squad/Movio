package com.madrid.presentation.viewModel.detailsViewModel

import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.presentation.viewModel.base.BaseViewModel
import com.madrid.presentation.viewModel.effect.effect

class MovieDetailsViewModel(
    private val artistUseCase: ArtistUseCase,
) : BaseViewModel<MovieDetailsUiState, effect>(
    MovieDetailsUiState()
) {
    fun loadCast(movieId: String) {
        tryToExecute(
            function = { artistUseCase.getArtistByQuery(query = movieId, page = 1) },
            onSuccess = { castList ->
                updateState {
                    it.copy(
                        cast = castList.map { artist ->
                            MovieDetailsUiState.CastUiState(
                                name = artist.name,
                                imageUrl = artist.imageUrl
                            )
                        },
                        isLoading = false
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }

            }
        )
    }
}