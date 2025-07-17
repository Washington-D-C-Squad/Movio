package com.madrid.data.repositories.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun searchMovieByQueryFromDB(query: String): List<MovieEntity>
    suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity>
    suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity>

    suspend fun getRecentSearches(): Flow<List<String>>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}