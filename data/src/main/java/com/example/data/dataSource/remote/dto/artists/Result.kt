package com.example.data.dataSource.remote.dto.artists


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("known_for")
    val knownFor: List<KnownFor> = listOf(),
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String = ""
)