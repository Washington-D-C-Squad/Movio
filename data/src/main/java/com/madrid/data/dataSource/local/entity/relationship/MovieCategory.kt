package com.madrid.data.dataSource.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.madrid.data.dataSource.local.entity.CategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity

@Entity(primaryKeys = ["categoryTitle", "movieId"])
data class MovieCategoryCrossRef(
    val categoryTitle: String,
    val movieId: Int
)

data class MovieWithCategories(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "categoryTitle",
        associateBy = Junction(MovieCategoryCrossRef::class)
    )
    val categories: List<CategoryEntity>
)

data class CategoryWithMovies(
    @Embedded val categories: CategoryEntity,
    @Relation(
        parentColumn = "categoryTitle",
        entityColumn = "movieId",
        associateBy = Junction(MovieCategoryCrossRef::class)
    )
    val movies: List<MovieEntity>
)