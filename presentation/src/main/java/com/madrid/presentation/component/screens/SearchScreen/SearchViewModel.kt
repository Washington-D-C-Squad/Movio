package com.madrid.presentation.component.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrid.presentation.component.screens.SearchScreen.MovieRepository
import com.madrid.presentation.component.screens.SearchScreen.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel()  {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            safeCall(
                onError = { msg ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Failed to load movies: $msg",
                        isLoading = false
                    )
                },
                onSuccess = {
                    val forYou = movieRepository.getForYouMovies()
                    val explore = movieRepository.getExploreMoreMovies()

                    _uiState.value = _uiState.value.copy(
                        forYouMovies = forYou,
                        exploreMoreMovies = explore,
                        isLoading = false
                    )
                }
            )
        }
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(searchResults = emptyList())
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            safeCall(
                onError = { msg ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Search failed: $msg",
                        isLoading = false
                    )
                },
                onSuccess = {
                    val results = movieRepository.searchMovies(query)
                    _uiState.value = _uiState.value.copy(
                        searchResults = results,
                        isLoading = false
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun navigateToMovieDetails(movieId: String) {
        // TODO: implement navigation logic
    }

    private suspend fun safeCall(
        onError: (String) -> Unit,
        onSuccess: suspend () -> Unit
    ) {
        try {
            onSuccess()
        } catch (e: Exception) {
            onError(e.message ?: "Unknown error")
        }
    }
}
