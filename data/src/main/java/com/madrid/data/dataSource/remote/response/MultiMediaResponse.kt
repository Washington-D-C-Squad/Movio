package com.madrid.data.dataSource.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultiMediaResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val multiMediaResults: List<MultiMediaResult> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class MultiMediaResult(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val gender: String = " ",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("media_type")
    val mediaType: String = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("first_air_date")
    val firstAirDate: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("origin_country")
    val originCountry: List<String> = listOf()
)


