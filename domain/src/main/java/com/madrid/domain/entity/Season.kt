package com.madrid.domain.entity

import kotlinx.datetime.LocalDate

data class Season(
    val id: Int,
    val seasonNumber: Int,
    val imageUrl: String,
    val rate: Double,
    val yearOfRelease: LocalDate,
    val episodes: List<Episode>,
    val description: String
)