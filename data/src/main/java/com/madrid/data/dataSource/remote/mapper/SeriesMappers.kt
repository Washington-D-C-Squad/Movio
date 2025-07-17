package com.madrid.data.dataSource.remote.mapper


import com.madrid.data.dataSource.remote.response.series.SeriesResult
import com.madrid.domain.entity.Series
import kotlinx.datetime.LocalDate


fun SeriesResult.toSeries(): Series {
    val releaseDataValue = if(this.releaseDate == null || this.releaseDate.isEmpty()){
        LocalDate.parse("2025-05-08")
    }else{
        LocalDate.parse(this.releaseDate)
    }
    return Series(
        id = this.id,
        title = this.title,
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.popularity ?: 0.0,
        yearOfRelease = releaseDataValue,
        seasons = null,
        description = this.overview ?: "",
        genre = listOf(),
        topCast = listOf(),
        reviews = listOf()
    )
}
