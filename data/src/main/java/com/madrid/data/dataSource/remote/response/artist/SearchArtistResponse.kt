package com.madrid.data.dataSource.remote.response.artist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchArtistResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val artistResults: List<ArtistsResult>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)

@Serializable
data class ArtistsResult(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("known_for")
    val knownForNetwork: List<KnownForNetwork>?,
    @SerialName("known_for_department")
    val role: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("profile_path")
    val profilePath: String?
)

@Serializable
data class KnownForNetwork(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    @SerialName("id")
    val id: Int?,
    @SerialName("media_type")
    val mediaType: String?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
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