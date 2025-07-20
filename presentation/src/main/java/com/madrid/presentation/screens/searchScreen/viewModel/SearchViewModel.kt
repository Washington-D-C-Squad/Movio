package com.madrid.presentation.screens.searchScreen.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.GetExploreMoreMovieUseCase
import com.madrid.domain.usecase.GetRecommendedMovieUseCase
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MovieUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.SeriesUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.paging.ExplorePagingSource
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
    private val movieUseCase: MovieUseCase,
    private val seriesUseCase: SeriesUseCase,

    private val preferredMediaUseCase: PreferredMediaUseCase,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val getExploreMoreMovieUseCase: GetExploreMoreMovieUseCase,
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
        viewModelScope.launch {
            val result = getRecommendedMovieUseCase(page = 1)
            updateState {
                it.copy(
                    searchUiState = it.searchUiState.copy(
                        forYouMovies = result.map { movie ->
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

        val result = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                ExplorePagingSource(getExploreMoreMovieUseCase)
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
                searchUiState = current.searchUiState.copy(
                    exploreMoreMovies = result,
                    isLoading = false
                ),
            )
        }

    }

    fun Movie.toMovieUiState(): SearchScreenState.MovieUiState {
        return SearchScreenState.MovieUiState(
            id = this.id.toString(),
            title = this.title,
            imageUrl = this.imageUrl,
            rating = this.rate.toString(),
            category = this.genre.toString()
        )
    }

    /*    fun List<Movie>.mapToMoviesUiState(): MutableList<SearchScreenState.MovieUiState> {
            var moviesUiState: MutableList<SearchScreenState.MovieUiState> = mutableListOf()
            this.forEach { media ->
                moviesUiState =
                    (moviesUiState + media.toMovieUiStateList().toMutableList()).toMutableList()
            }
            return moviesUiState
        }


        fun Movie.toMovieUiStateList(): List<SearchScreenState.MovieUiState> {
            val moviesUiState: MutableList<SearchScreenState.MovieUiState> = mutableListOf()
            movies.forEach { movie ->
                moviesUiState.add(
                    movie.toMovieUiState()

                )
            }
            return moviesUiState
        }*/

    fun searchMovies(query: String) {
        tryToExecute(
            function = { movieUseCase.invoke(query) },
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
        Log.d("hay", "in search filtered movies fn $query")
        val result = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ), pagingSourceFactory = {
                SearchMoviePagingSource(query, movieUseCase)
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
        Log.d("enter search", "nooooooooooooooooooooobbbbbbb: $query")
        val result = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ), pagingSourceFactory = {
                SearchSeriesPagingSource(query, seriesUseCase)
            }
        ).flow
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { series ->
                    Log.d("enter search", "searchSeries: ${series}")
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
        Log.d("hay", "in top results fn $query")

        val result: Flow<PagingData<SearchScreenState.MovieUiState>> = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ), pagingSourceFactory = {
                SearchMoviePagingSource(
                    query = query,
                    movieUseCase = movieUseCase
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
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ), pagingSourceFactory = {
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


//    fun getData(query: String) {
//        Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = {
//                SearchMoviePagingSource(
//                    query = query,
//                    movieUseCase = movieUseCase
//                )
//            }
//        ).flow.cachedIn(viewModelScope).map { pagingItem ->
//            pagingItem.map {
//                it.let { item ->
//                    SearchScreenState.MovieUiState(
//                        title = item.title,
//                        id = it.id.toString(),
//                        imageUrl = it.imageUrl,
//                        rating = it.rate.toString(),
//                    )
//                }
//            }
//        }.also { result ->
//            updateState { it.copy(searchUiState = it.searchUiState.copy(searchResults = result)) }
//        }
//    }

    companion object {
        @JvmStatic
        fun clearRecentSearchesStatic() {
        }
    }
}



