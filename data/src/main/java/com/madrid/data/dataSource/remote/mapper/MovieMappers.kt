package com.madrid.data.dataSource.remote.mapper

import com.madrid.data.dataSource.remote.response.MovieResult
import com.madrid.domain.entity.Movie
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import java.time.format.DateTimeFormatter


fun MovieResult.toMovie(): Movie {
        val releaseDataValue = if(this.releaseDate == null || this.releaseDate.isEmpty()){
            LocalDate.parse("2025-05-08")
        }else{
            LocalDate.parse(this.releaseDate)
        }
    return Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.popularity ?: 0.0,
        yearOfRelease =releaseDataValue,
        movieDuration = "",
        description = this.overview ?: "",
        genre = listOf(),
        topCast = listOf(),
        reviews = listOf()
    )
}