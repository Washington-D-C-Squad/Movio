package com.madrid.presentation.screens.reviewsScreen

data class ReviewsScreenUiState(
    val reviews: List<Review> = emptyList(),
)

data class Review(
    val reviewerName: String,
    val reviewerImageUrl: String,
    val rating: Float,
    val date: String,
    val content: String
)