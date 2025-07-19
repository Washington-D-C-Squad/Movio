package com.madrid.data.dataSource.remote.response.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SeriesDetailsResponse(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("created_by")
    val director: List<Director>?,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>?,
    @SerialName("first_air_date")
    val firstAirDate: String?,
    @SerialName("genres")
    val genres: List<SeriesGenres>?,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("in_production")
    val inProduction: Boolean?,
    @SerialName("languages")
    val languages: List<String>?,
    @SerialName("last_air_date")
    val lastAirDate: String?,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: FinaleEpisode?,
    @SerialName("name")
    val name: String?,
    @SerialName("next_episode_to_air")
    val nextEpisode: String?,
    @SerialName("networks")
    val channels: List<Channels>?,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerialName("origin_country")
    val originCountry: List<String>?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanies>?,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountries>?,
    @SerialName("seasons")
    val seasons: List<SeasonsNetwork>?,
    @SerialName("spoken_languages")
    val spokenLanguages: List<Translation>?,
    @SerialName("status")
    val status: String?,
    @SerialName("tagline")
    val tagline: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?,
)

@Serializable
data class Director(
    @SerialName("id")
    val id: Int?,
    @SerialName("credit_id")
    val creditId: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("original_name")
    val originalName: String?,
    @SerialName("gender")
    val gender: Int?,
    @SerialName("profile_path")
    val profilePath: String?,
)

@Serializable
data class SeriesGenres(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
)

@Serializable
data class FinaleEpisode(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?,
    @SerialName("air_date")
    val airDate: String?,
    @SerialName("episode_number")
    val episodeNumber: Int?,
    @SerialName("production_code")
    val productionCode: String?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("season_number")
    val seasonNumber: Int?,
    @SerialName("show_id")
    val showId: Int?,
    @SerialName("still_path")
    val stillPath: String?,
)

@Serializable
data class Channels(
    @SerialName("id")
    val id: Int?,
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin_country")
    val originCountry: String?,
)

@Serializable
data class ProductionCompanies(
    @SerialName("id")
    val id: Int?,
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin_country")
    val originCountry: String?,
)

@Serializable
data class ProductionCountries(
    @SerialName("iso_3166_1")
    val iso: String?,
    @SerialName("name")
    val name: String?,
)

@Serializable
data class SeasonsNetwork(
    @SerialName("air_date")
    val airDate: String?,
    @SerialName("episode_count")
    val episodeCount: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("season_number")
    val seasonNumber: Int?,
    @SerialName("vote_average")
    val voteAverage: Double?,
)

@Serializable
data class Translation(
    @SerialName("english_name")
    val englishName: String?,
    @SerialName("iso_639_1")
    val iso: String?,
    @SerialName("name")
    val name: String?,
)