package com.madrid.domain.entity

import kotlinx.datetime.LocalDate

data class Artist(
    val id: Int,
    val name:String,
    val role: String,
    //null for not found in actual response
    val dateOfBirth: LocalDate?,
    val country: String?,
    val description: String?,
    val imageUrl : String,
)
