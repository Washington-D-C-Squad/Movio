<<<<<<<< HEAD:presentation/src/main/java/com/example/presentation/viewModels/SearchViewModel.kt
package com.example.presentation.viewModels
========
package com.madrid.presentation.component.viewmodel
>>>>>>>> develop:presentation/src/main/java/com/madrid/presentation/component/screens/SearchScreen/SearchViewModel.kt

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<<< HEAD:presentation/src/main/java/com/example/presentation/viewModels/SearchViewModel.kt
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.presentation.searchScreens.MovieRepository
import com.example.presentation.component.screens.SearchScreen.SearchUiState
import com.example.presentation.worker.RecentSearchSyncWorker
========
import com.madrid.presentation.component.screens.SearchScreen.MovieRepository
import com.madrid.presentation.component.screens.SearchScreen.SearchUiState
>>>>>>>> develop:presentation/src/main/java/com/madrid/presentation/component/screens/SearchScreen/SearchViewModel.kt
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