package com.madrid.data.repositories.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertMovie(movie: MovieEntity)

    suspend fun insertSeries(series: SeriesEntity)
    suspend fun insertArtist (artist: ArtistEntity)

    suspend fun getTopRatedMovies(): List<MovieEntity>

    suspend fun searchMovieByQueryFromDB(query: String): List<MovieEntity>
    suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity>
    suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity>

    suspend fun getRecentSearches():List<RecentSearchEntity>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}