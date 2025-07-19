package com.madrid.domain.entity

data class Series(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val seasons: List<Season> = listOf(),
    val description: String,
    val genre: List<String>? = listOf(),
    val profilePage: String = " "
)

data class SimilarSeries(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
)
