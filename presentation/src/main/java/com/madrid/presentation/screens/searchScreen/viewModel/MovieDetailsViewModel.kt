package com.madrid.presentation.screens.searchScreen.viewModel

import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.screens.searchScreen.viewModel.base.BaseViewModel

class MovieDetailsViewModel(
    private val artistUseCase: ArtistUseCase,
    override val recentSearchUseCase: RecentSearchUseCase,
) : BaseViewModel<MovieDetailsUiState>(MovieDetailsUiState()) {

    fun loadCast(movieId: String) {
        tryToExecute(
            function = { artistUseCase.getArtistByQuery(query = movieId , page = 1 ) },
            onSuccess = { castList ->
                updateState {
                    it.copy(
                        cast = castList.map {artist ->
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
                        isLoading = false ,
                        errorMessage = error.message
                    )
                }

            }
        )
    }
}