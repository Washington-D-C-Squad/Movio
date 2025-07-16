package com.madrid.presentation.screens.searchScreen

import androidx.lifecycle.viewModelScope
import com.madrid.domain.entity.Media
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.math.exp

@KoinViewModel
class SearchViewModel(
    private val artistUseCase: ArtistUseCase,
    private val mediaUseCase: MediaUseCase,
    private val preferredMediaUseCase: PreferredMediaUseCase,
    override val recentSearchUseCase: RecentSearchUseCase,
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

    fun addToRecentSearches(query: String) {
        val currentList = state.value.recentSearchUiState.toMutableList()
        currentList.remove(query)
        currentList.add(0, query)
        val newList = currentList.take(10)
        updateState { it.copy(recentSearchUiState = newList) }
    }

    fun removeRecentSearch(searchItem: String) {
        val currentList = state.value.recentSearchUiState.toMutableList()
        currentList.remove(searchItem)
        updateState { it.copy(recentSearchUiState = currentList) }
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
                 mediaUseCase.getTopRatedMedia("fafafdf")
            },
            onSuccess = { (forYou, explore) ->
                viewModelScope.launch {
                    forYou.collect {movies->
                        updateState {
                            it.copy(
                                searchUiState = it.searchUiState.copy(
                                    forYouMovies =  movies.map { movie ->
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
                    }
                }

                viewModelScope.launch {
                    forYou.collect {movies->
                        updateState {
                            it.copy(
                                searchUiState = it.searchUiState.copy(
                                    exploreMoreMovies =  movies.map { movie ->
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
                    }
                }




//                updateState {
//                    it.copy(
//                        searchUiState = it.searchUiState.copy(
//                            forYouMovies = forYou.mapToMoviesUiState(),
//                            exploreMoreMovies = explore.toMovieUiStateList(),
//                            isLoading = false
//                        )
//                    )
//                }
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

    override fun onSearchSubmit() {
        val query = searchQuery.value.trim()
        if (query.isNotBlank()) {
            addToRecentSearches(query)
            _searchQuery.value = ""
        }
    }

    companion object {
        @JvmStatic
        fun clearRecentSearchesStatic() {
        }
    }
}