package com.example.data.dataSource.local.data.entity


import kotlinx.datetime.LocalDate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SERIES_TABLE")
data class SeriesEntity (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: LocalDate,
    val description: String,
)