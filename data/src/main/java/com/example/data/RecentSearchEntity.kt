package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class RecentSearchEntity(
    @PrimaryKey val id: String,
    val query: String,
    val timestamp: Long,
    val searchCount: Int? = null
) 