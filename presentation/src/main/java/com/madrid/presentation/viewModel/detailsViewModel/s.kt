package com.madrid.presentation.screens.detailsScreen.similarMovies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madrid.domain.entity.SimilarMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class SimilarMoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<SimilarMovie> = emptyList(),
    val errorMessage: String? = null
)

class SimilarMoviesViewModel(
    private val repository: SimilarMoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SimilarMoviesUiState())
    val uiState: StateFlow<SimilarMoviesUiState> = _uiState.asStateFlow()

    fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            try {
                val movies = withContext(Dispatchers.IO) {
                    repository.getSimilarMovies(movieId)
                }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    movies = movies
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun retry(movieId: Int) {
        loadSimilarMovies(movieId)
    }
}

// Repository Interface
interface SimilarMoviesRepository {
    suspend fun getSimilarMovies(movieId: Int): List<SimilarMovie>
}

// Repository Implementation
class SimilarMoviesRepositoryImpl(
    private val apiService: MoviesApiService
) : SimilarMoviesRepository {

    override suspend fun getSimilarMovies(movieId: Int): List<SimilarMovie> {
        return apiService.getSimilarMovies(movieId).map { apiMovie ->
            SimilarMovie(
                id = apiMovie.id,
                title = apiMovie.title,
                imageUrl = "https://image.tmdb.org/t/p/w500${apiMovie.posterPath}",
                rate = apiMovie.voteAverage
            )
        }
    }
}

// API Service Interface
interface MoviesApiService {
    suspend fun getSimilarMovies(movieId: Int): List<ApiSimilarMovie>
}

// API Response Data Class
data class ApiSimilarMovie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val voteAverage: Double
)

// API Service Implementation (example with basic HTTP client)
class MoviesApiServiceImpl : MoviesApiService {

    // You can replace this with Retrofit, Ktor, or any other HTTP client
    override suspend fun getSimilarMovies(movieId: Int): List<ApiSimilarMovie> {
        // Example implementation - replace with your actual API call
        return try {
            // Make API call here
            // For example, using OkHttp or Retrofit
            fetchFromApi(movieId)
        } catch (e: Exception) {
            throw Exception("Failed to fetch similar movies: ${e.message}")
        }
    }

    private suspend fun fetchFromApi(movieId: Int): List<ApiSimilarMovie> {
        // Placeholder implementation
        // Replace this with your actual API call
        return emptyList()
    }
}

// Factory for creating ViewModel without Hilt
class SimilarMoviesViewModelFactory(
    private val repository: SimilarMoviesRepository
) : androidx.lifecycle.ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimilarMoviesViewModel::class.java)) {
            return SimilarMoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// Extension function to create ViewModel in Composable
@Composable
fun createSimilarMoviesViewModel(): SimilarMoviesViewModel {
    val apiService = remember { MoviesApiServiceImpl() }
    val repository = remember { SimilarMoviesRepositoryImpl(apiService) }
    val factory = remember { SimilarMoviesViewModelFactory(repository) }

    return viewModel(factory = factory)
}