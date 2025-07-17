package com.madrid.data.dataSource.remote.response.artist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDetailsResponse(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("also_known_as")
    val nickName: List<String>?,
    @SerialName("biography")
    val biography: String?,
    @SerialName("birthday")
    val birthDay: String?,
    @SerialName("deathday")
    val deathDay: String?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("homepage")
    val homePage: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("known_for_department")
    val role: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("place_of_birth")
    val placeOfBirth: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("profile_path")
    val profilePath: String?
)