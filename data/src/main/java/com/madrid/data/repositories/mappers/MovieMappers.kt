package com.madrid.data.repositories.mappers

import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.domain.entity.Movie


fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease,
        movieDuration = this.movieDuration,
        description = this.description,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease,
        movieDuration = this.movieDuration,
        description = this.description,
        genre = TODO(),
        topCast = TODO(),
        reviews = TODO(),
    )
}

