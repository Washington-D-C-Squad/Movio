package com.madrid.domain.entity

data class Series(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val seasons: Int = 0,
)


data class SearchResult(
    val page: Int?,
    val artistResults: List<Any>?,
    val totalPages: Int?,
    val totalResults: Int?
)
