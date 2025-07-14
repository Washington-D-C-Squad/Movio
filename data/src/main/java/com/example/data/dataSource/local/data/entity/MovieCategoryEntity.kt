package com.example.data.dataSource.local.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORY_TABLE")
data class MovieCategoryEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
)
