package com.madrid.presentation.viewModel.detailsViewModel

import androidx.lifecycle.viewModelScope
import com.madrid.domain.repository.MovieDetailsRepository
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val artistUseCase: MovieDetailsRepository,
    override val recentSearchUseCase: RecentSearchUseCase,
) : BaseViewModel<MovieDetailsUiState>(MovieDetailsUiState()) {

    fun loadCast(movieId: String) {
        tryToExecute(
            function = {
                artistUseCase.getMovieDetailsById(movieId.toInt())
            },
            onSuccess = {
                updateState {
                    it.copy(
                        cast = it.cast
                    )
                }
            },
            onError =
                { error ->
                    updateState {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Unknown error occurred"
                        )
                    }
                }
        )
    }

}