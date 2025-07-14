package com.example.data.repositories.mappers

import com.example.data.dataSource.local.data.entity.MovieEntity
import com.example.domain.entity.Movie


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

