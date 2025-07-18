package com.madrid.data.dataSource.local

import android.content.Context
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.datasource.LocalDataSource

class LocalDataSourceImpl(
    private val context: Context
) : LocalDataSource {


    private val dao = MovioDatabase.getInstance(context)

    override suspend fun insertMovie(movie: MovieEntity) {
        dao.movieDao().insertMovie(movie)
    }

    override suspend fun getMovieById(movieId: Int): MovieEntity? {
        return dao.movieDao().getMovieById(movieId)
    }

    override suspend fun insertSeries(series: SeriesEntity) {
        dao.seriesDao().insertSeries(series)
    }

    override suspend fun getTopRatedMovies(): List<MovieEntity> {
        return dao.movieDao().getTopRatedMovies()
    }

    override suspend fun insertArtist(artist: ArtistEntity) {
        dao.artistDao().insertArtist(artist = artist)
    }


    override suspend fun searchMovieByQueryFromDB(query: String): List<MovieEntity> {
        return dao.movieDao().getMovieByTitle("%$query%")

    }

    override suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity> {
        return dao.seriesDao().getSeriesByTitle("%$query%")
    }

    override suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity> {
        return dao.artistDao().getArtistByName("%$query%")
    }


    override suspend fun getRecentSearches(): List<RecentSearchEntity> {
        return dao.recentSearchDao().getRecentSearches()
    }

    override suspend fun addRecentSearch(item: String) {
        dao.recentSearchDao().addRecentSearch(
            RecentSearchEntity(
                searchQuery = item,
            )
        )
    }

    override suspend fun removeRecentSearch(item: String) {
        dao.recentSearchDao().removeRecentSearch(
           item
        )
    }

    override suspend fun clearAllRecentSearches() {
        dao.recentSearchDao().clearAllRecentSearches()
    }

}