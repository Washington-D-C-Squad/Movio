package com.example.domain

data class RecentSearchItem(
    val id: String,
    val id: Long = 0,
    val query: String,
    val timestamp: Long,
    val searchCount: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
)