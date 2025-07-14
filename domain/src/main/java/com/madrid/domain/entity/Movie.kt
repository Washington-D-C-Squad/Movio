package com.madrid.domain.entity

import kotlinx.datetime.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: LocalDate,
    val movieDuration: String,
    val description: String,
    val genre: List<String>,
    val topCast: List<Artist>,
    val reviews: List<Review>
)

