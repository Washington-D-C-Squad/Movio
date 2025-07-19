package com.madrid.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String = "",
    val movieDuration: String = "",
    val description: String = "",
    val genre: List<String>? = listOf(),
    val crew: List<Cast> = listOf(),
    val profilePage: String = " "
)

data class Cast(
    val id: Int,
    val name: String,
    val imageUrl: String,

    )


data class Trailer(
    val key: String,
    val id: String
)

data class SimilarMovie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
)


