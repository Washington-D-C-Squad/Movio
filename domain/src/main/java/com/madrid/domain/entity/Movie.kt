package com.madrid.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val movieDuration: String,
    val description: String,
    val genre: List<String>,
)

