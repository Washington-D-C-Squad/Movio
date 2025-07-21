package com.madrid.data.dataSource.local.mappers

import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.domain.entity.Movie
import kotlinx.datetime.LocalDate


fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease.toString(),
        movieDuration = this.movieDuration,
        description = this.description,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.movieId,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = LocalDate.parse(this.yearOfRelease).toString(),
        movieDuration = this.movieDuration,
        description = this.description,
        genre = listOf(),

        )
}

