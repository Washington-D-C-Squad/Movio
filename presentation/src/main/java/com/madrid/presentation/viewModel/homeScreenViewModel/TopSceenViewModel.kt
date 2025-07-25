package com.madrid.presentation.viewModel.homeScreenViewModel

import com.madrid.domain.usecase.generUseCase.GetMovieGenresUseCase
import com.madrid.domain.usecase.homeUseCase.MoviesByGenresUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.viewModel.base.BaseViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieUiState
import com.madrid.presentation.viewModel.detailsViewModel.Movie
import com.madrid.presentation.viewModel.effect.effect
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TopScreenViewModel(
    private val moviesByGenresUseCase: MoviesByGenresUseCase,
    private val getTopRatedMoviesUseCase: TrendingMediaUseCase,
    private val getGenresUseCase: GetMovieGenresUseCase
) : BaseViewModel<DetailsMovieUiState, effect>(
    DetailsMovieUiState()
) {

    private var trendingMedia: List<Movie> = emptyList()

    init {
        loadGenres()
        loadTrendingMedia()
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

    private fun loadTrendingMedia() {
        tryToExecute(
            function = { getTopRatedMoviesUseCase() },
            onSuccess = { result ->
                val uiMovies = result.map { domainMovie ->
                    Movie(
                        id = domainMovie.id,
                        imageUrl = domainMovie.imageUrl,
                        rate = domainMovie.rate,
                        name = domainMovie.title,
                        genre = domainMovie.genre
                    )
                }
                trendingMedia = uiMovies
                updateState { it.copy(filteredMovies = uiMovies) }
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
                updateState { it.copy(filteredMovies = filtered) }
            },
            onError = {
            }
        )
    }
}



