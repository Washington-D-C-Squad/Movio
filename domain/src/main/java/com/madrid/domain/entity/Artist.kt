package com.madrid.domain.entity

data class Artist(
    val id: Int,
    val name:String,
    val role: String,
    val dateOfBirth: String,
    val country: String,
    val description: String,
    val imageUrl : String,
)
