package com.madrid.presentation.screens.searchScreen.viewModel

import androidx.lifecycle.viewModelScope
import com.madrid.domain.entity.Media
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.viewModel.base.BaseViewModel
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
            recentSearchUseCase.getRecentSearches().collect { result ->
                updateState { it.copy(recentSearchUiState = result) }
            }
        }

    }

    fun addRecentSearch(recentSearch: String) {
        viewModelScope.launch {
            recentSearchUseCase.addRecentSearch(item = recentSearch)
            recentSearchUseCase.getRecentSearches().collect { result ->
                updateState {
                    it.copy(recentSearchUiState = result)
                }
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            recentSearchUseCase.clearAllRecentSearches()
            recentSearchUseCase.getRecentSearches().collect { result ->
                updateState {
                    it.copy(recentSearchUiState = result)
                }
            }

        }
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
                viewModelScope.launch {
                    /*     forYou.collect { movies ->
                             updateState {
                                 it.copy(
                                     searchUiState = it.searchUiState.copy(
                                         forYouMovies = movies.map { movie ->
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
                         }*/
                }
                viewModelScope.launch {
                    /*     forYou.collect { movies ->
                             updateState {
                                 it.copy(
                                     searchUiState = it.searchUiState.copy(
                                         exploreMoreMovies = movies.map { movie ->
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
                         }*/
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
        /*     tryToExecute(
                 function = { mediaUseCase.getMovieByQuery(query).first() },
                 onSuccess = { result ->
                     Log.e("MY_TAG","$result this is here ")
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
             )*/
    }

    override fun onSearchSubmit() {
        val query = searchQuery.value.trim()
        if (query.isNotBlank()) {
            addToRecentSearches(query)
            _searchQuery.value = ""
        }
    }


    fun searchFilteredMovies(query: String) {
        /*   tryToExecute(
               function = { mediaUseCase.getMovieByQuery(query).first() },
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
           )*/
    }

    fun searchSeries(query: String) {
        viewModelScope.launch {
            /*         mediaUseCase.getSeriesByQuery(query).collect {
                         updateState { currentState ->
                             Log.e("MY_TAGG","i am here in suceess ")
                             currentState.copy(
                                 filteredScreenUiState = currentState.filteredScreenUiState.copy(
                                     series = it.map { series ->
                                         SearchScreenState.SeriesUiState(
                                             id = series.id.toString(),
                                             title = series.title.toString(),
                                             imageUrl = series.imageUrl.toString(),
                                             rating = series.rate.toString()
                                         )
                                     },
                                     isLoading = false
                                 )
                             )
                         }
                     }*/

        }
//        tryToExecute(
//            function = { mediaUseCase.getSeriesByQuery(query).first() },
//            onSuccess = { result ->
//                Log.e("MY_TAGG"," this is here  $result ")
//
//                updateState { currentState ->
//                    currentState.copy(
//                        filteredScreenUiState = currentState.filteredScreenUiState.copy(
//                            series = result.map { series ->
//                                SearchScreenState.SeriesUiState(
//                                    id = series.id.toString(),
//                                    title = series.title.toString(),
//                                    imageUrl = series.imageUrl.toString(),
//                                    rating = series.rate.toString()
//                                )
//                            },
//                            isLoading = false
//                        )
//                    )
//                }
//            },
//            onError = {
//                Log.e("MY_TAGG","error")
//                updateState { currentState ->
//                    currentState.copy(
//                        searchUiState = currentState.searchUiState.copy(
//                            isLoading = false,
//                            errorMessage = "Failed to load series"
//                        )
//                    )
//                }
//            }
//        )
    }

    fun topResult(query: String) {
        /* tryToExecute(
             function = { mediaUseCase.getMovieByQuery(query).first() },
             onSuccess = { result ->
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
                 updateState { current ->
                     current.copy(
                         filteredScreenUiState = current.filteredScreenUiState.copy(
                             isLoading = false,
                             errorMessage = "Failed to load top result"
                         )
                     )
                 }
             }
         )*/
    }

    fun artists(query: String) {
        viewModelScope.launch {
            /*      Log.e("MY_TAGG"," i am in call   ")
                  artistUseCase.getArtistByQuery(query).collect {
                      updateState { currentState ->
                          currentState.copy(
                              filteredScreenUiState = currentState.filteredScreenUiState.copy(
                                  artist = it.map { artist ->
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
                  }*/
        }
//        tryToExecute(
//            function = { artistUseCase.getArtistByQuery(query).first() },
//            onSuccess = { result ->
//                updateState { currentState ->
//                    currentState.copy(
//                        filteredScreenUiState = currentState.filteredScreenUiState.copy(
//                            artist = result.map { artist ->
//                                SearchScreenState.ArtistUiState(
//                                    id = artist.id.toString(),
//                                    name = artist.name,
//                                    imageUrl = artist.imageUrl.toString(),
//                                )
//                            },
//                            isLoading = false
//                        )
//                    )
//                }
//            },
//            onError = {
//                Log.e("MY_TAGG"," erorr here  ")
//
//                updateState { currentState ->
//                    currentState.copy(
//                        filteredScreenUiState = currentState.filteredScreenUiState.copy(
//                            isLoading = false,
//                            errorMessage = "Failed to load "
//                        )
//                    )
//                }
//            }
//        )
    }

    companion object {
        @JvmStatic
        fun clearRecentSearchesStatic() {
        }
    }
}



