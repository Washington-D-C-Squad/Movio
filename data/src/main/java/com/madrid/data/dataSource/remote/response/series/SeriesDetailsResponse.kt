package com.madrid.data.dataSource.remote.response.series

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class SeriesDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("created_by")
    val director: List<Director> = listOf(),
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int> = listOf(),
    @SerializedName("first_air_date")
    val firstAirDate: String = "",
    @SerializedName("genres")
    val genres: List<Genres> = listOf(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("in_production")
    val inProduction: Boolean = false,
    @SerializedName("languages")
    val languages: List<String> = listOf(),
    @SerializedName("last_air_date")
    val lastAirDate: String = "",
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: FinaleEpisode = FinaleEpisode(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("next_episode_to_air")
    val nextEpisode: String = "",
    @SerializedName("networks")
    val channels: List<Channels> = listOf(),
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int = 0,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0,
    @SerializedName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanies> = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries> = listOf(),
    @SerializedName("seasons")
    val seasons: List<Seasons> = listOf(),
    @SerializedName("spoken_languages")
    val spokenLanguages: List<Translation> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,

    )


@Serializable
data class Director(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("credit_id")
    val creditId: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("profile_path")
    val profilePath: String = "",
)

@Serializable
data class Genres(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",

    )

@Serializable
data class FinaleEpisode(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("air_date")
    val airDate: String = "",
    @SerializedName("episode_number")
    val episodeNumber: Int = 0,
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
)

@Serializable
data class Channels(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = "",
)

@Serializable
data class ProductionCompanies(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = "",

    )

@Serializable
data class ProductionCountries(
    @SerializedName("iso_3166_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = "",
)

@Serializable
data class Seasons(
    @SerializedName("air_date")
    val airDate: String = "",
    @SerializedName("episode_count")
    val episodeCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
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
data class Translation(
    @SerializedName("english_name")
    val englishName: String = "",
    @SerializedName("iso_639_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = "",
)