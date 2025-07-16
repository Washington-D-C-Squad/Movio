package com.madrid.data.dataSource.remote.response.movie

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCredits(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("cast")
    val cast: List<Cast> = listOf(),
)

@Serializable
data class Cast(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String = "",
    @SerializedName("cast_id")
    val castId: Int = 0,
    @SerializedName("character")
    val character: String = "",
    @SerializedName("credit_id")
    val creditId: String = "",
    @SerializedName("order")
    val order: Int = 0
)