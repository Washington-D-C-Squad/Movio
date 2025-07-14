package com.example.data.repositories.mappers


import com.example.data.dataSource.local.data.entity.ArtistEntity
import com.example.domain.entity.Artist

fun Artist.toArtistEntity(): ArtistEntity {
    return ArtistEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        role = this.role,
        dateOfBirth = this.dateOfBirth,
        country = this.country
    )
}

fun ArtistEntity.toArtist(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        role = this.role,
        dateOfBirth = this.dateOfBirth,
        country = this.country
    )
}
