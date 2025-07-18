package com.madrid.presentation.screens.searchScreen.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madrid.domain.entity.Media
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.viewModel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

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


    private fun loadRecentSearches() {

        viewModelScope.launch {
            tryToExecute(
                function = { recentSearchUseCase.getRecentSearches() },
                onSuccess = { result ->
                    updateState {
                        it.copy(
                            recentSearchUiState = result
                        )
                    }
                },
                onError = { },
                scope = viewModelScope,
                dispatcher = Dispatchers.IO
            )

        }

    }

    fun addRecentSearch(recentSearch: String) {
        tryToExecute(
            function = {
                recentSearchUseCase.addRecentSearch(item = recentSearch)
                recentSearchUseCase.getRecentSearches()
            },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }

    fun clearAll() {
        tryToExecute(
            function = {
                recentSearchUseCase.clearAllRecentSearches()
                recentSearchUseCase.getRecentSearches()
            },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }


    fun addToRecentSearches(query: String) {
        tryToExecute(
            function = {
                recentSearchUseCase.addRecentSearch(query)

            },
            onSuccess = {

            },
            onError = {

            },
            scope = viewModelScope,
            dispatcher = Dispatchers.IO
        )
    }

    fun removeRecentSearch(searchItem: String) {
        tryToExecute(
            function = {
                recentSearchUseCase.removeRecentSearch(searchItem)

            },
            onSuccess = {

            },
            onError = {

            },
            scope = viewModelScope,
            dispatcher = Dispatchers.IO
        )
    }

    fun loadInitialData() {

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

                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            forYouMovies = forYou.map { movie ->
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
                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            exploreMoreMovies = explore.map { movie ->
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
            function = { mediaUseCase.getMovieByQuery(query) },
            onSuccess = { result ->
                Log.e("MY_TAG", "$result this is here ")
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


    fun searchFilteredMovies(query: String) {
        tryToExecute(
            function = { mediaUseCase.getMovieByQuery(query) },
            onSuccess = { result ->
                updateState { current ->
                    current.copy(
                        filteredScreenUiState = current.filteredScreenUiState.copy(
                            movie = result.map { movie ->
                                SearchScreenState.MovieUiState(
                                    title = movie.title,
                                    id = movie.id.toString(),
                                    imageUrl = movie.imageUrl,
                                    rating = movie.rate.toString(),
                                )
                            }
                        ),
                        searchUiState = current.searchUiState.copy(isLoading = false)
                    )
                }
            },
            onError = {}
        )
    }

    fun searchSeries(query: String) {
        tryToExecute(
            function = { mediaUseCase.getSeriesByQuery(query) },
            onSuccess = { result ->
                updateState { current ->
                    current.copy(
                        filteredScreenUiState = current.filteredScreenUiState.copy(
                            series = result.map { series ->
                                SearchScreenState.SeriesUiState(
                                    id = series.id.toString(),
                                    title = series.title.toString(),
                                    imageUrl = series.imageUrl.toString(),
                                    rating = series.rate.toString()
                                )
                            },
                            isLoading = false
                        ),
                        searchUiState = current.searchUiState.copy(isLoading = false)
                    )
                }
            },
            onError = {}
        )
    }

    fun topResult(query: String) {
        tryToExecute(
            function = { mediaUseCase.getMovieByQuery(query) },
            onSuccess = { result ->
                Log.e("MY_TAG", "$result")

                updateState { current ->
                    current.copy(
                        filteredScreenUiState = current.filteredScreenUiState.copy(
                            topResult = result.map { rate ->
                                SearchScreenState.MovieUiState(
                                    id = rate.id.toString(),
                                    title = rate.title,
                                    imageUrl = rate.imageUrl,
                                    rating = rate.rate.toString()
                                )
                            },
                            isLoading = false
                        )
                    )
                }
            },
            onError = {
                Log.e("MY_TAG", "${it.message}")

                updateState { current ->
                    current.copy(
                        filteredScreenUiState = current.filteredScreenUiState.copy(
                            isLoading = false,
                            errorMessage = "Failed to load top result"
                        )
                    )
                }
            }
        )
    }

    fun artists(query: String) {
        tryToExecute(
            function = {
                artistUseCase.getArtistByQuery(query)
            },
            onSuccess = { result ->
                Log.d("MY_TAG" , "in view model $result")
                updateState { current ->
                    current.copy(
                        filteredScreenUiState = currentState.filteredScreenUiState.copy(
                            artist = result.map { artist ->
                                SearchScreenState.ArtistUiState(
                                    id = artist.id.toString(),
                                    name = artist.name,
                                    imageUrl = artist.imageUrl.toString(),
                                )
                            },
                            isLoading = false
                        )
                    )
                }
            },
            onError = {
                Log.d("MY_TAG" , "in view model ${it.message}")
                updateState { current ->
                    current.copy(
                        filteredScreenUiState = current.filteredScreenUiState.copy(
                            isLoading = false,
                            errorMessage = "Failed to load top result"
                        )
                    )
                }
            }
        )
    }

    companion object {
        @JvmStatic
        fun clearRecentSearchesStatic() {

        }
    }
}



