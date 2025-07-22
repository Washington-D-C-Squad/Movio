package com.madrid.data.dataSource.local.mappers

import com.madrid.data.dataSource.local.entity.CategoryEntity
import com.madrid.data.dataSource.remote.response.movie.MovieGenre


fun MovieGenre.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id ?: 0,
        categoryTitle = this.name ?: "Unknown",
        searchCount = 0
    )
}