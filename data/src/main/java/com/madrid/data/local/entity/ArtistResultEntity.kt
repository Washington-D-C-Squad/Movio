package com.madrid.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist_results")
data class ArtistResultEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val profilePath: String?,
    val knownForDepartment: String?
) 