package com.example.domain.entity

import kotlinx.datetime.LocalDate

data class Artist(
    val id: Int,
    val name:String,
    val role: String,
    val dateOfBirth: LocalDate,
    val country: String,
    val description: String,
    val imageUrl : String,
)
