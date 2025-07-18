package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "RECENT_TABLE")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = false) val searchQuery: String,
    val timestamp: Long = System.currentTimeMillis()
)
