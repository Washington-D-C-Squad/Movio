package com.madrid.presentation.viewModel.searchViewModel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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
import com.madrid.presentation.viewModel.base.BaseViewModel
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


    fun searchMovies(query: String) {
        tryToExecute(
            function = { movieUseCase.invoke(query) },
            onSuccess = { result ->
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
                    SearchScreenState.SeriesUiState(
                        id = series.id.toString(),
                        title = series.title,
                        imageUrl = series.imageUrl,
                        rating = series.rate.toString(),
                    )
                }
            }

        updateState { current ->
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

    fun highlightCharactersInText(
        fullText: String,
        query: String,
        matchColor: Color,
        normalColor: Color,
        textStyle: TextStyle
    ): AnnotatedString {
        val builder = AnnotatedString.Builder()

        if (query.isBlank()) {
            builder.pushStyle(textStyle.copy(color = normalColor).toSpanStyle())
            builder.append(fullText)
            return builder.toAnnotatedString()
        }

        fullText.forEach { char ->
            val isMatch = query.contains(char, ignoreCase = true)
            val color = if (isMatch) matchColor else normalColor

            builder.pushStyle(textStyle.copy(color = color).toSpanStyle())
            builder.append(char.toString())
            builder.pop()
        }

        return builder.toAnnotatedString()
    }
    
}



