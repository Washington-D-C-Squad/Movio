package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORY_TABLE")
data class CategoryEntity(
    val categoryTitle: String,
    @PrimaryKey(autoGenerate = false)  val categoryId: Int,
    val searchCount: Int = 0,
)
