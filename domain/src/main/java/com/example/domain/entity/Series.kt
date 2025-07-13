package com.example.domain.entity

import kotlinx.datetime.LocalDate

data class Series(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: LocalDate,
    val seasons: List<Season>,
    val description: String,
    val genre: List<String>,
    val topCast: List<Artist>,
    val reviews: List<Review>
)


