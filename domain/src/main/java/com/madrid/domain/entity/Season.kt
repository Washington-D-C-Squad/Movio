package com.madrid.domain.entity

data class Season(
    val id: Int,
    val seasonNumber: Int,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val numOfEpisodes: Int,
    val description: String
)