package com.madrid.data.dataSource.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchSeriesResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerialName("results")
    val seriesResults: List<SeriesResult> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0,
)

@Serializable
data class SeriesResult(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("first_air_date")
    val releaseDate: String = "",
    @SerialName("name")
    val title: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)
