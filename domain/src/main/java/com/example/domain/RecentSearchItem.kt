package com.example.domain

data class RecentSearchItem(
    val id: String,
    val query: String,
    val timestamp: Long,
    val searchCount: Int? = null
) 