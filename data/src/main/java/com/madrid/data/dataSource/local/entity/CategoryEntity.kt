package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORY_TABLE")
data class CategoryEntity(
    val id: Int,
    @PrimaryKey(autoGenerate = false) val categoryTitle: String,
    val searchCount: Int = 0,
)