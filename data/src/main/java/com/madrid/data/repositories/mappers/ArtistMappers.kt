package com.madrid.data.repositories.mappers


import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.domain.entity.Artist
import kotlinx.datetime.LocalDate

fun Artist.toArtistEntity(): ArtistEntity {
    return ArtistEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        description = this.description,
        role = this.role,
        dateOfBirth = this.dateOfBirth.toString(),
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
        dateOfBirth = LocalDate.parse(this.dateOfBirth),
        country = this.country
    )
}
