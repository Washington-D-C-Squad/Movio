package com.madrid.data.dataSource.local.mappers


import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.domain.entity.Series
import kotlinx.datetime.LocalDate

fun Series.toSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease.toString(),
        description = this.description,
    )
}

fun SeriesEntity.toSeries(): Series {
    return Series(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = LocalDate.parse(this.yearOfRelease),
        description = this.description,
        genre = listOf(),
        topCast = listOf(),
        reviews = listOf(),
        seasons = listOf(),
    )
}

