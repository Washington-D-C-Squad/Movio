package com.madrid.domain.entity

data class Review(
    val mediaId: Int = 0,
    val userId: Int,
    val rate: Double,
    val dateOfRelease: String,
    val comment: String,
)


data class ReviewResult(
    val mediaId: Int,
    val page: Int,
    val results: List<Review>,
    val totalPages: Int,
    val totalResults: Int
)