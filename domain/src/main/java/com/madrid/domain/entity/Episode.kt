package com.madrid.domain.entity

data class Episode(
    val id: Int,
    val name: String,
    val episodeNumber: Int,
    val duration: String,
    val imageUrl : String,
    val rate : Double,
)
