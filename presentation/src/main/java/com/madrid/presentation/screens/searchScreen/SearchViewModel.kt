package com.madrid.presentation.screens.searchScreen

import com.madrid.domain.entity.Media
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.first
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val artistUseCase: ArtistUseCase,
    private val mediaUseCase: MediaUseCase,
    private val preferredMediaUseCase: PreferredMediaUseCase,
    private val recentSearchUseCase: RecentSearchUseCase,
    private val trendingMediaUseCase: TrendingMediaUseCase,
) : BaseViewModel<SearchScreenState>(
    SearchScreenState()
) {
    init {
        loadRecentSearches()
        loadInitialData()
    }

    fun loadRecentSearches() {
        tryToExecute(
            function = { recentSearchUseCase.getRecentSearches().first() },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }

    fun addRecentSearch(recentSearch: String) {
        tryToExecute(
            function = {
                recentSearchUseCase.addRecentSearch(item = recentSearch)
                recentSearchUseCase.getRecentSearches().first()
            },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }


    fun clearAll() {
        tryToExecute(
            function = {
                recentSearchUseCase.clearAllRecentSearches()
                recentSearchUseCase.getRecentSearches().first()
            },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }

    private fun loadInitialData() {
        updateState {
            it.copy(
                searchUiState = it.searchUiState.copy(
                    isLoading = true,
                    errorMessage = null
                )
            )
        }

        tryToExecute(
            function = {
                val forYou = preferredMediaUseCase.getPreferredMedia()
                val explore = trendingMediaUseCase.getTrendingMedia()
                forYou to explore
            },
            onSuccess = { (forYou, explore) ->
                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            forYouMovies = forYou.mapToMoviesUiState(),
                            exploreMoreMovies = explore.toMovieUiStateList(),
                            isLoading = false
                        )
                    )
                }
            },
            onError = { e ->
                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            errorMessage = "Failed to load movies: ${e.message}",
                            isLoading = false
                        )
                    )
                }
            }
        )
    }

    fun List<Media>.mapToMoviesUiState(): MutableList<SearchScreenState.MovieUiState> {
        var moviesUiState: MutableList<SearchScreenState.MovieUiState> = mutableListOf()
        this.forEach { media ->
            moviesUiState =
                (moviesUiState + media.toMovieUiStateList().toMutableList()).toMutableList()
        }
        return moviesUiState
    }


    fun Media.toMovieUiStateList(): List<SearchScreenState.MovieUiState> {

        val moviesUiState: MutableList<SearchScreenState.MovieUiState> = mutableListOf()
        movies.forEach { movie ->
            moviesUiState.add(
                SearchScreenState.MovieUiState(
                    id = movie.id.toString(),
                    title = movie.title,
                    imageUrl = movie.imageUrl,
                    rating = movie.rate.toString(),
                    category = movie.genre.first()
                )
            )
        }
        return moviesUiState

    }

    fun searchMovies(query: String) {
        tryToExecute(
            function = { mediaUseCase.getMovieByQuery(query).first() },
            onSuccess = { result ->
                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            searchResults = result.map { movie ->
                                SearchScreenState.MovieUiState(
                                    title = movie.title,
                                    id = movie.id.toString(),
                                    imageUrl = movie.imageUrl,
                                    rating = movie.rate.toString(),
                                )
                            },
                            isLoading = false
                        )
                    )
                }
            },
            onError = {}
        )
    }
}