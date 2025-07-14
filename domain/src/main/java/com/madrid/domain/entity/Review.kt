package com.example.domain.entity

import kotlinx.datetime.LocalDate

data class Review(
    val id: Int,
    val userId: Int,
    val rate: Double,
    val dateOfRelease: LocalDate,
    val comment: String
)
