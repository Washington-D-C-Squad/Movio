package com.example.presentation.viewModel.base

import com.example.domain.RecentSearchItem
import com.example.domain.interfaces.SearchRepository

class SearchViewModel(
    private val searchRepository: SearchRepository ,
) : BaseViewModel<SearchScreenState>(
    SearchScreenState()
) {
    init {
        loadRecentSearches()
        loadInitialData()
    }

    fun loadRecentSearches() {
        tryToExecute(
            function = { searchRepository.getRecentSearches() },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }

    fun addRecentSearch(item: RecentSearchItem) {
        tryToExecute(
            function = {
                searchRepository.addRecentSearch(item)
                searchRepository.getRecentSearches()
            },
            onSuccess = { result -> updateState { it.copy(recentSearchUiState = result) } },
            onError = {}
        )
    }

    fun clearAll() {
        tryToExecute(
            function = {
                searchRepository.clearAllRecentSearches()
                searchRepository.getRecentSearches()
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
                val forYou = searchRepository.getTopRatedMovies("action").map { it.toUiState() }
                val explore = searchRepository.getTopRatedMovies("drama").map { it.toUiState() }
                forYou to explore
            },
            onSuccess = { (forYou, explore) ->
                updateState {
                    it.copy(
                        searchUiState = it.searchUiState.copy(
                            forYouMovies = forYou,
                            exploreMoreMovies = explore,
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
}