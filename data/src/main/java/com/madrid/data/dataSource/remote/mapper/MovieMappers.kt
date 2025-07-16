package com.madrid.data.dataSource.remote.mapper

import com.madrid.data.dataSource.remote.response.movie.MovieResult
import com.madrid.domain.entity.Movie
import kotlinx.datetime.LocalDate



fun MovieResult.toMovie(): Movie {
        return Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.popularity ?: 0.0,
        yearOfRelease = LocalDate.parse(this.releaseDate.toString()),
        movieDuration = "",
        description = this.overview ?: "",
        genre = listOf(),
        topCast = listOf(),
        reviews = listOf()
    )
}