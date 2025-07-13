package com.example.presentation.component.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.presentation.component.screens.SearchScreen.MovieRepository
import com.example.presentation.component.screens.SearchScreen.SearchUiState
import com.example.presentation.worker.RecentSearchSyncWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel()  {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
        scheduleCacheRecentSearchWorker()
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

    fun navigateToMovieDetails(movieId: String) {
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

    private fun scheduleCacheRecentSearchWorker() {
        val context = getApplicationContextSafely()
        if (context != null) {
            val workRequest = PeriodicWorkRequestBuilder<RecentSearchSyncWorker>(1, TimeUnit.HOURS)
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "RecentSearchSyncWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }

    private fun getApplicationContextSafely(): Context? {
        return try {
            null
        } catch (e: Exception) {
            null
        }
    }
}
