package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SERIES_GENRE_TABLE")
data class SeriesGenreEntity(
    val genreTitle: String,
    @PrimaryKey(autoGenerate = false) val genreId: Int,
    val searchCount: Int = 0,
)