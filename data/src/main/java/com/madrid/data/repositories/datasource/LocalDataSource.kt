package com.madrid.data.repositories.datasource

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity

interface LocalDataSource {

    suspend fun getMovieById(movieId: Int): MovieEntity?
    suspend fun getSeriesById(seriesId: Int): SeriesEntity?
    suspend fun getArtistById(artistId: Int): ArtistEntity?

    suspend fun insertSeries(series: SeriesEntity)
    suspend fun insertArtist (artist: ArtistEntity)
    suspend fun insertMovie(movie: MovieEntity)

    suspend fun getTopRatedMovies(): List<MovieEntity>

    suspend fun searchMovieByQueryFromDB(query: String): List<MovieEntity>
    suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity>
    suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity>

    suspend fun getRecentSearches():List<RecentSearchEntity>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}