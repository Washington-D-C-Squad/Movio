package com.madrid.domain.entity

data class RecentSearch(
    val id: Int,
    val name: String
)

data class SearchResult(
    val page: Int?,
    val searchResults: List<Any>?,
    val totalPages: Int?,
    val totalResults: Int?
)