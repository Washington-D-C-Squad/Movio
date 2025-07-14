package com.madrid.data.repositories.mappers


import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.example.domain.entity.Series

fun Series.toSeriesEntity(): SeriesEntity {
    return SeriesEntity(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease,
        description = this.description,
    )
}

fun SeriesEntity.toSeries(): Series {
    return Series(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        rate = this.rate,
        yearOfRelease = this.yearOfRelease,
        description = this.description,
        genre = TODO(),
        topCast = TODO(),
        reviews = TODO(),
        seasons = TODO(),
    )
}

