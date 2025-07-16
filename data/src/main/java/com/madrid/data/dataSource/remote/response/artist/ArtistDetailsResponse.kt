package com.madrid.data.dataSource.remote.response.artist

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ArtistDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("also_known_as")
    val nickName: List<String> = listOf(),
    @SerializedName("biography")
    val biography: String = "",
    @SerializedName("birthday")
    val birthDay: String = "",
    @SerializedName("deathday")
    val deathDay: String = "",
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("homepage")
    val homePage: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("known_for_department")
    val role: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("place_of_birth")
    val placeOfBirth: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String = ""
)