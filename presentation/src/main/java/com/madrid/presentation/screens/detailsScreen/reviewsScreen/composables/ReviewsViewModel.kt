package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReviewsViewModel : ViewModel() {
    // Use proper naming convention with single underscore for mutable state
    private val _state = mutableStateOf(ReviewsScreenUiState())
    val state: State<ReviewsScreenUiState> = _state

    fun loadReviews(mediaId: String, isMovie: Boolean) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                // Replace with actual repository call
                val reviews = emptyList<ReviewUiState>() // Temporary placeholder

                _state.value = _state.value.copy(
                    reviews = reviews,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load reviews"
                )
            }
        }
    }
}

// These data classes should be in the same file or imported
data class ReviewsScreenUiState(
    val reviews: List<ReviewUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class ReviewUiState(
    val id: String,
    val userName: String,
    val reviewDate: String,
    val rating: Int,
    val reviewText: String
)