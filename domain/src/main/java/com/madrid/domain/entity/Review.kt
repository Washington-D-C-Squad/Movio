package com.madrid.domain.entity

data class Review(
    val mediaId: Int,
    val userId: Int,
    val rate: Double,
    val dateOfRelease: String,
    val comment: String,
)
