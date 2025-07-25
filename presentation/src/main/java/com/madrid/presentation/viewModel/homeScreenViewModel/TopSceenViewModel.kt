package com.madrid.presentation.viewModel.homeScreenViewModel

import android.util.Log
import com.madrid.domain.usecase.homeUseCase.GetTopRatedMoviesUseCase
import com.madrid.domain.usecase.generUseCase.GetMovieGenresUseCase
import com.madrid.domain.usecase.homeUseCase.MoviesByGenresUseCase
import com.madrid.presentation.viewModel.base.BaseViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieUiState
import com.madrid.presentation.viewModel.detailsViewModel.Movie
import com.madrid.presentation.viewModel.effect.effect
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TopScreenViewModel(
    private val moviesByGenresUseCase: MoviesByGenresUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getGenresUseCase: GetMovieGenresUseCase
) : BaseViewModel<DetailsMovieUiState, effect>(
    DetailsMovieUiState()
) {


    init {
        loadGenres()
        loadTopRatedMedia()
    }

    private fun loadGenres() {
        tryToExecute(
            function = { getGenresUseCase() },
            onSuccess = { genres ->
                updateState { it.copy(genreMovie = genres) }
            },
            onError = { /* Handle if needed */ }
        )
    }

    private fun loadTopRatedMedia() {
        tryToExecute(
            function = {
                getTopRatedMoviesUseCase(page = 6)
            },
            onSuccess = { result ->
                val uiMovies = result.map { movie ->
                    Movie(
                        id = movie.id,
                        imageUrl = movie.imageUrl,
                        rate = movie.rate,
                        name = movie.title,
                        genre = movie.genre
                    )
                }
                updateState {
                    Log.d("log items", "TopRatingScreen: $uiMovies")
                    it.copy(filteredMovies = uiMovies)
                }
            },
            onError = {
            }
        )
    }

    fun onGenreSelected(genre: String) {
        tryToExecute(
            function = { moviesByGenresUseCase(genre) },
            onSuccess = { result ->
                val moviesList = result[genre] ?: emptyList()
                val filtered = moviesList.map { movie ->
                    Movie(
                        id = movie.id,
                        name = movie.title,
                        rate = movie.rate,
                        imageUrl = movie.imageUrl,
                        genre = listOf(genre)
                    )
                }
                updateState {
                    Log.d("log items", "TopRatingScreen: $filtered")
                    it.copy(filteredMovies =filtered ) }
            },
            onError = {
            }
        )
    }
}