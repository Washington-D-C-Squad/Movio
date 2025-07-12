package com.example.domain

data class RecentSearchItem(
    val id: Long = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
) 