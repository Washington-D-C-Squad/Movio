package com.madrid.data.dataSource.remote.mapper

import com.madrid.domain.entity.ArtisKnownFor
import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Episode

data class MediaGenre(
    val id: Int,
    val title: String
)

data class Credits(
    val id: Int,
    val cast: List<Cast>?,
)

data class SimilarMedia(
    val page: Int?,
    val results: List<Any>?,
    val totalPages: Int?,
    val totalResults: Int?,
)

data class SeasonEpisodes(
    val seriesId: Int,
    val seasonNumber: Int,
    val episodes: List<Episode>,
)

data class SearchResult(
    val page: Int?,
    val searchResults: List<Any>?,
    val totalPages: Int?,
    val totalResults: Int?
)

data class KnownForMovies(
    val knownForMovies: List<ArtisKnownFor>?,
)

