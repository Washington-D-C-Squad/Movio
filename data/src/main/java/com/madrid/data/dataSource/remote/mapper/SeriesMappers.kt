package com.madrid.data.dataSource.remote.mapper


import com.madrid.data.dataSource.remote.response.series.SeriesResult
import com.madrid.domain.entity.Series


fun SeriesResult.toSeries(): Series {
    return Series(
        id = this.id ?: 0,
        title = this.title ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.popularity ?: 0.0,
        yearOfRelease = this.releaseDate ?: "",
        description = this.overview ?: "",
        genre = listOf(),
    )
}
