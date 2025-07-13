package com.madrid.domain

data class RecentSearchItem(
    val id: Long,
    val query: String,
    val searchCount: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
)