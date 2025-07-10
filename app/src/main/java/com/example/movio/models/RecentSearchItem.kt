package com.example.movio.models

data class RecentSearchItem(
    val id: String,
    val query: String,
    val timestamp: Long,
    val searchCount: Int? = null
)
