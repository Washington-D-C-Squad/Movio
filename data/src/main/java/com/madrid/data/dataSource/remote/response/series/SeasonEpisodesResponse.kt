package com.madrid.data.dataSource.remote.response.series

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class SeasonEpisodesResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("air_date")
    val releaseDate: String = "",
    @SerializedName("episodes")
    val episodes: List<Episode> = listOf(),
    @SerializedName("name")
    val title: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
)

@Serializable
data class Episode(
    @SerializedName("air_date")
    val airDate: String = "",
    @SerializedName("episode_number")
    val episodeNumber: Int = 0,
    @SerializedName("episode_type")
    val episodeType: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("production_code")
    val productionCode: String = "",
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("season_number")
    val seasonNumber: Int = 0,
    @SerializedName("show_id")
    val showId: Int = 0,
    @SerializedName("still_path")
    val stillPath: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("crew")
    val crew: List<Crew> = listOf(),
    @SerializedName("guest_stars")
    val guestStars: List<GuestStar> = listOf(),
)

@Serializable
data class Crew(
    @SerializedName("department")
    val department: String = "",
    @SerializedName("job")
    val job: String = "",
    @SerializedName("credit_id")
    val creditId: String = "",
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
)

@Serializable
data class GuestStar(
    @SerializedName("character")
    val character: String = "",
    @SerializedName("credit_id")
    val creditId: String = "",
    @SerializedName("order")
    val order: Int = 0,
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
)