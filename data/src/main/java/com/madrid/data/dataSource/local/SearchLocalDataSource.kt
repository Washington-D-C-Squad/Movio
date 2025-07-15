package com.madrid.data.dataSource.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.SearchLocalSource
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSource(
    private val database: MovioDatabase
) : SearchLocalSource {

//    private val database = MovioDatabase.Companion.getInstance(context)

    override suspend fun insertMovie(movie: MovieEntity) {
        database.movieDao().insertMovie(movie)
    }

    // movies
    override fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return database.movieDao().getTopRatedMovies()
    }

    override fun getMoviesByTitle(query: String): Flow<List<MovieEntity>> {
        return database.movieDao().getMovieByTitle("%$query%")
    }

    // recent searches
    override fun getRecentSearches(): Flow<List<MovieCategoryEntity>> {
        return database.movieCategoryDao().getAllCategoriesBySearchCount()
    }


    // series
    override suspend fun insertSeries(series: SeriesEntity) {
        database.seriesDao().insertSeries(series)
    }

    override fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>> {
        return database.seriesDao().getSeriesByTitle("%$query%")
    }

    override fun getTopRatedSeries(): Flow<List<SeriesEntity>> {
        return database.seriesDao().getTopRatedSeries()
    }

    // artists
    override fun getArtistsByName(query: String): Flow<List<ArtistEntity>> {
        return database.artistDao().getArtistByName("%$query%")
    }


}