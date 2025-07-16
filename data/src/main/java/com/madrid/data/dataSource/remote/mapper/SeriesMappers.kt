package com.madrid.data.dataSource.remote.mapper


import com.madrid.data.dataSource.remote.response.series.SeriesResult
import com.madrid.domain.entity.Series
import kotlinx.datetime.LocalDate


fun SeriesResult.toSeries(): Series {
    return Series(
        id = this.id,
        title = this.title,
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.popularity ?: 0.0,
        yearOfRelease = LocalDate.parse(this.releaseDate ?: ""),
        seasons = null,
        description = this.overview ?: "",
        genre = listOf(),
        topCast = listOf(),
        reviews = listOf()
    )
}
