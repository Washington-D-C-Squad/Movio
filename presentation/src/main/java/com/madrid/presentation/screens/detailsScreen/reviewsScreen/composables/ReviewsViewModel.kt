//package com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.madrid.presentation.viewModel.detailsViewModel.ReviewUiState
//import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState
//import kotlinx.coroutines.launch
//
//class ReviewsViewModel : ViewModel() {
//    // Use proper naming convention with single underscore for mutable state
//    private val _state = mutableStateOf(ReviewsScreenUiState())
//    val state: State<ReviewsScreenUiState> = _state
//
//    fun loadReviews(mediaId: String, isMovie: Boolean) {
//        viewModelScope.launch {
//            _state.value = _state.value.copy(isLoading = true)
//            try {
//                // Replace with actual repository call
//                val reviews = emptyList<ReviewUiState>() // Temporary placeholder
//
//                _state.value = _state.value.copy(
//                    reviews = reviews,
//                )
//            } catch (e: Exception) {
//                _state.value = _state.value.copy(
//                    error = e.message ?: "Unknown error",
//                    isLoading = false
//                )
//            }
//        }
//    }
//}
