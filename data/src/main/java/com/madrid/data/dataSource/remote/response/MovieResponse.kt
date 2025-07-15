package com.madrid.data.dataSource.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val movieResults: List<MovieResult> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
)


@Serializable
data class MovieResult(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
)

//{
//    "adult": false,
//    "backdrop_path": null,
//    "belongs_to_collection": null,
//    "budget": 0,
//    "genres": [
//    {
//        "id": 99,
//        "name": "Documentary"
//    }
//    ],
//    "homepage": "http://www.hbo.com/documentaries/studs-terkel-listening-to-america/index.html#/",
//    "id": 248890,
//    "imdb_id": "tt1660413",
//    "origin_country": [
//    "US"
//    ],
//    "original_language": "en",
//    "original_title": "Studs Terkel: Listening to America",
//    "overview": "For over 60 years, Studs Terkel elevated the voices and experiences of everyday Americans through his skillful interviews on radio, in books and on TV. This documentary takes a fond and illuminating look back at one of America's most influential authors and media personalities whose curiosity about people never dimmed over the course of a long and brilliant career.",
//    "popularity": 0.1263,
//    "poster_path": "/vpiZwg2HMGbaYjm3eWhjuJ9Tz65.jpg",
//    "production_companies": [
//    {
//        "id": 27965,
//        "logo_path": null,
//        "name": "Lucky Duck Productions",
//        "origin_country": "US"
//    },
//    {
//        "id": 27966,
//        "logo_path": null,
//        "name": "Educational Film Center",
//        "origin_country": ""
//    },
//    {
//        "id": 14914,
//        "logo_path": "/1RZBWz53SpHUTLpRcM8BGv2plIP.png",
//        "name": "HBO Documentary Films",
//        "origin_country": "US"
//    }
//    ],
//    "production_countries": [
//    {
//        "iso_3166_1": "US",
//        "name": "United States of America"
//    }
//    ],
//    "release_date": "2009-05-15",
//    "revenue": 0,
//    "runtime": 40,
//    "spoken_languages": [
//    {
//        "english_name": "English",
//        "iso_639_1": "en",
//        "name": "English"
//    }
//    ],
//    "status": "Released",
//    "tagline": "Documentary about the life and career of the author and pioneer of radio & television, Studs Terkel.",
//    "title": "Studs Terkel: Listening to America",
//    "video": false,
//    "vote_average": 5.5,
//    "vote_count": 3
//}