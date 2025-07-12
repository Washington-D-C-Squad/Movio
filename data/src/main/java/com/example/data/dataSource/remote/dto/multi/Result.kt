package com.example.data.dataSource.remote.dto.multi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("first_air_date")
    val firstAirDate: String?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("genre_ids")
    val genreIds: List<Int?>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("known_for")
    val knownFor: List<KnownFor?>?,
    @SerialName("known_for_department")
    val knownForDepartment: String?,
    @SerialName("media_type")
    val mediaType: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin_country")
    val originCountry: List<String?>?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("profile_path")
    val profilePath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
)