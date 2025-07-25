package com.madrid.data.dataSource.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SERIES_TABLE")
data class SeriesEntity (
    @PrimaryKey(autoGenerate = false) val seriesId: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: String,
    val description: String,
)