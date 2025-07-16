package com.madrid.data.dataSource.remote.mapper


import com.madrid.data.dataSource.remote.response.artist.ArtistsResult
import com.madrid.domain.entity.Artist

fun ArtistsResult.toArtist(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        role = this.role,
        dateOfBirth = null,
        country = null,
        description = null,
        imageUrl = "https://image.tmdb.org/t/p/original${this.profilePath}",
    )
}
