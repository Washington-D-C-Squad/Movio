package com.madrid.data.dataSource.local.mappers

import com.madrid.data.dataSource.local.entity.MovieGenreEntity
import com.madrid.data.dataSource.remote.response.genre.MovieGenre


fun MovieGenre.toCategoryEntity(): MovieGenreEntity {
    return MovieGenreEntity(
        genreId = this.id ?: 0,
        genreTitle = this.name ?: "Unknown",
        searchCount = 0
    )
}