package com.madrid.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "multi_results")
data class MultiResultEntity(
    @PrimaryKey val id: Int,
    val name: String?,
    val title: String?,
    val mediaType: String?,
    val profilePath: String?
) 