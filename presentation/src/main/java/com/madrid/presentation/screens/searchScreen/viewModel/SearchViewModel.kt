package com.madrid.presentation.screens.searchScreen.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.madrid.domain.entity.Media
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.paging.SearchArtistPagingSource
import com.madrid.presentation.screens.searchScreen.paging.SearchMoviePagingSource
import com.madrid.presentation.screens.searchScreen.paging.SearchSeriesPagingSource
import com.madrid.presentation.screens.searchScreen.viewModel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
                mediaUseCase.getTopRatedMedia(
                    page = 1
                )
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
//                            searchResults = result.map { movie ->
//                                SearchScreenState.MovieUiState(
//                                    title = movie.title,
//                                    id = movie.id.toString(),
//                                    imageUrl = movie.imageUrl,
//                                    rating = movie.rate.toString(),
//                                )
//                            },
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
        val result = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(query, mediaUseCase)
            }
        ).flow
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { movie ->
                    SearchScreenState.MovieUiState(
                        id = movie.id.toString(),
                        title = movie.title,
                        imageUrl = movie.imageUrl,
                        rating = movie.rate.toString()
                    )
                }
            }

        updateState { current ->
            current.copy(
                filteredScreenUiState = current.filteredScreenUiState.copy(
                    movie = result,
                    isLoading = false
                ),
                searchUiState = current.searchUiState.copy(isLoading = false)
            )
        }
    }


    fun searchSeries(query: String) {
        val result = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                val x = SearchSeriesPagingSource(query, mediaUseCase)
//                Log.d("series", "searchSeries: ${x}")
                x
            }
        ).flow
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { series ->
                    Log.d("series", "searchSeries: ${series}")
                    SearchScreenState.SeriesUiState(
                        id = series.id.toString(),
                        title = series.title,
                        imageUrl = series.imageUrl,
                        rating = series.rate.toString(),
                    )
                }
            }

        updateState { current ->
//            Log.d("series", "searchSeries: ${current.filteredScreenUiState.series}")
            current.copy(
                filteredScreenUiState = current.filteredScreenUiState.copy(
                    series = result,
                    isLoading = false
                ),
                searchUiState = current.searchUiState.copy(isLoading = false)
            )
        }
    }


    fun topResult(query: String) {
        val result: Flow<PagingData<SearchScreenState.MovieUiState>> = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    query = query,
                    mediaUseCase = mediaUseCase
                )
            }
        ).flow
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { item ->
                    SearchScreenState.MovieUiState(
                        id = item.id.toString(),
                        title = item.title,
                        imageUrl = item.imageUrl,
                        rating = item.rate.toString()
                    )
                }
            }

        updateState { current ->
            current.copy(
                filteredScreenUiState = current.filteredScreenUiState.copy(
                    topResult = result,
                    isLoading = false
                )
            )
        }
    }


    fun artists(query: String) {
        val result = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchArtistPagingSource(query, artistUseCase)
            }
        ).flow
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { artist ->
                    SearchScreenState.ArtistUiState(
                        id = artist.id.toString(),
                        name = artist.name,
                        imageUrl = artist.imageUrl,
                        role = artist.role ?: "",
                        country = artist.country,
                        description = artist.description
                    )
                }
            }

        updateState { current ->
            current.copy(
                filteredScreenUiState = current.filteredScreenUiState.copy(
                    artist = result,
                    isLoading = false
                )
            )
        }
    }


    fun getData(query: String) {
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    query = query,
                    mediaUseCase = mediaUseCase
                )
            }
        ).flow.cachedIn(viewModelScope).map { pagingItem ->
            pagingItem.map {
                it.let { item ->
                    SearchScreenState.MovieUiState(
                        title = item.title,
                        id = it.id.toString(),
                        imageUrl = it.imageUrl,
                        rating = it.rate.toString(),
                    )
                }
            }
        }.also { result ->
            updateState { it.copy(searchUiState = it.searchUiState.copy(searchResults = result)) }
        }
    }

    companion object {
        @JvmStatic
        fun clearRecentSearchesStatic() {

        }
    }
}



