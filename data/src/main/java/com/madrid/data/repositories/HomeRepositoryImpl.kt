package com.madrid.data.repositories

import com.madrid.data.dataSource.local.mappers.toMovie
import com.madrid.data.dataSource.local.mappers.toSeries
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : HomeRepository {
    override suspend fun getMoviesByGenres(): Map<String, List<Movie>> {
        val genresWithMovies = localDataSource.getMoviesByGenres()
        return genresWithMovies.associate { genreWithMovies ->
            val genreTitle = genreWithMovies.genre.genreTitle
            val movies = genreWithMovies.movies.map { it.toMovie() }
            genreTitle to movies
        }
    }

    override suspend fun getSeriesByGenres(): Map<String, List<Series>> {
        val genresWithSeries = localDataSource.getSeriesByGenres()
        return genresWithSeries.associate { genreWithSeries ->
            val genreTitle = genreWithSeries.genre.genreTitle
            val series = genreWithSeries.series.map { it.toSeries() }
            genreTitle to series
        }
    }
    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        val response = remoteDataSource.getTopRatedMovies(page)
        return response.movieResults?.map { dto ->
            Movie(
                id = dto.id ?: 0,
                title = dto.title ?: "",
                imageUrl = dto.posterPath ?:"",
                rate = dto.voteAverage?: 1.0,
                yearOfRelease =dto.releaseDate ?:"",
                description = dto.overview?:"",
                genre = listOf(dto.genreIds.toString()),
            )
        } ?: emptyList()
    }

}