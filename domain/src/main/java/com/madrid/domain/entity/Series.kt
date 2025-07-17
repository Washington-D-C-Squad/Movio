package com.madrid.domain.entity

data class Series(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val numOfSeasons: Int,
    val description: String,
    val genre: List<String>,
)


