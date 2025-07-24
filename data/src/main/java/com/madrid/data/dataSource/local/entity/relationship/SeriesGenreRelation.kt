package com.madrid.data.dataSource.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.dataSource.local.entity.SeriesGenreEntity

@Entity(tableName = "SeriesGenreCrossRef", primaryKeys = ["genreId", "seriesId"])
data class SeriesGenreCrossRef(
    val genreId: Int,
    val seriesId: Int
)

data class SeriesWithGenres(
    @Embedded val series: SeriesEntity,
    @Relation(
        parentColumn = "seriesId",
        entityColumn = "genreId",
        associateBy = Junction(SeriesGenreCrossRef::class)
    )
    val genres: List<SeriesGenreEntity>
)

data class GenreWithSeries(
    @Embedded val genre: SeriesGenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "seriesId",
        associateBy = Junction(SeriesGenreCrossRef::class)
    )
    val series: List<SeriesEntity>
)