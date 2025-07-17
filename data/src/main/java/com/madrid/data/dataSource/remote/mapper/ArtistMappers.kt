package com.madrid.data.dataSource.remote.mapper


import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.ArtistsResult
import com.madrid.data.dataSource.remote.response.artist.KnownFor
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.domain.entity.ArtisKnownFor
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.SearchResult

fun ArtistDetailsResponse.toArtist(): Artist {
    return Artist(
        id = this.id ?: 0,
        name = this.name ?: "",
        role = this.role ?: "",
        dateOfBirth = this.birthDay ?: "",
        country = this.placeOfBirth ?: "",
        description = this.biography ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.profilePath}",
    )
}

fun ArtistsResult.toArtist(): Artist {
    return Artist(
        id = this.id ?: 0,
        name = this.name ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.profilePath}",
        artisKnownFor = this.knownFor?.map { it.toArtistKnownFor() },
    )
}

fun KnownFor.toArtistKnownFor(): ArtisKnownFor {
    return ArtisKnownFor(
        id = this.id ?: 0,
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        title = this.title ?: "",
        voteAverage = this.voteAverage ?: 0.0,
    )
}

fun SearchArtistResponse.toSearchResult(): SearchResult {
    return SearchResult(
        page = this.page,
        artistResults = this.artistResults?.map { it.toArtist() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

