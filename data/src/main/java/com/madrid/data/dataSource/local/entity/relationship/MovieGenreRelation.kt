package com.madrid.data.dataSource.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.madrid.data.dataSource.local.entity.MovieGenreEntity
import com.madrid.data.dataSource.local.entity.MovieEntity

@Entity(tableName = "MovieCategoryCrossRef", primaryKeys = ["genreId", "movieId"])
data class MovieCategoryCrossRef(
    val genreId: Int,
    val movieId: Int
)

data class MovieWithGenres(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieCategoryCrossRef::class)
    )
    val categories: List<MovieGenreEntity>
)

data class GenreWithMovies(
    @Embedded val categories: MovieGenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(MovieCategoryCrossRef::class)
    )
    val movies: List<MovieEntity>
)