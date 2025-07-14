package com.madrid.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "ARTIST_TABLE")
data class ArtistEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val role: String,
    val dateOfBirth: LocalDate,
    val country: String,
    val description: String,
    val imageUrl: String,
)
