package com.madrid.data.dataSource.remote.response.artist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDetailsResponse(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("also_known_as")
    val nickName: List<String>? = null,
    @SerialName("biography")
    val biography: String? = null,
    @SerialName("birthday")
    val birthDay: String? = null,
    @SerialName("deathday")
    val deathDay: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("homepage")
    val homePage: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("known_for_department")
    val role: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("profile_path")
    val profilePath: String? = null
)