package com.madrid.presentation.screens.searchScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val mediaUseCase: MediaUseCase,
) : ViewModel() {

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
                    val (movies) = mediaUseCase.getTopRatedMedia("")

                    _uiState.value = _uiState.value.copy(
                        forYouMovies = movies,
                        exploreMoreMovies =movies,
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
                    Log.i("SearchVMErr", "searchMoviesByName:$msg ")

                },
                onSuccess = {
                    mediaUseCase.getMovieByQuery(query).collect {
                        _uiState.value = _uiState.value.copy(
                            searchResults = it,
                            isLoading = false
                        )
                        Log.i("SearchVmSuccess", "searchMoviesByName:$it ")

                    }

                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
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
