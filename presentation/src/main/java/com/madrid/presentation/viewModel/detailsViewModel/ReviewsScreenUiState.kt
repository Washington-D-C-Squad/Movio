package com.madrid.presentation.viewModel.detailsViewModel

import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewUiState

data class ReviewsScreenUiState(
    val reviews: List<ReviewUiState> = emptyList(),
)

data class ReviewUiState(
    val reviewerName: String,
    val reviewerImageUrl: String,
    val rating: Float,
    val date: String,
    val content: String
)