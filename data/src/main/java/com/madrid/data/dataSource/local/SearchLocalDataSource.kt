package com.madrid.data.dataSource.local

import android.content.Context
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.SearchLocalSource
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSource(
    private val context: Context
) : SearchLocalSource {


    private val dao = MovioDatabase.Companion.getInstance(context)

    suspend fun insertMovie(movie: MovieEntity) {
        dao.movieDao().insertMovie(movie)
    }

    suspend fun insertSeries(series: SeriesEntity) {
        dao.seriesDao().insertSeries(series)
    }

    // movies
    override fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return dao.movieDao().getTopRatedMovies()
    }

    override fun getMoviesByTitle(query: String): Flow<List<MovieEntity>> {
        return dao.movieDao().getMovieByTitle("%$query%")
    }

    // recent searches
    override fun getRecentSearches(): Flow<List<MovieCategoryEntity>> {
        return dao.movieCategoryDao().getAllCategoriesBySearchCount()
    }


    // series
    override fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>> {
        return dao.seriesDao().getSeriesByTitle("%$query%")
    }

    override fun getTopRatedSeries(): Flow<List<SeriesEntity>> {
        return dao.seriesDao().getTopRatedSeries()
    }

    // artists
    override fun getArtistsByTitle(query: String): Flow<List<ArtistEntity>> {
        return dao.artistDao().getArtistByName("%$query%")
    }


}