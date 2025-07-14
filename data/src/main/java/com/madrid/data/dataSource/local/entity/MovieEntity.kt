package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "MOVIE_TABLE")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: LocalDate,
    val movieDuration: String,
    val description: String,
)