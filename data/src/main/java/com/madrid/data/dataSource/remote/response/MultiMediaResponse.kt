package com.madrid.data.dataSource.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MultiMediaResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val multiMediaResults: List<MultiMediaResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class MultiMediaResult(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val gender: String = " ",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("media_type")
    val mediaType: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("first_air_date")
    val firstAirDate: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("origin_country")
    val originCountry: List<String> = listOf()
)


