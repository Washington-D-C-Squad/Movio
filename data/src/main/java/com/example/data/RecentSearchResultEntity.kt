package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_results")
data class RecentSearchResultEntity(
    @PrimaryKey val queryKey: String, // query + language
    val query: String,
    val language: String,
    val resultJson: String, // Store the result as JSON
    val timestamp: Long
) 