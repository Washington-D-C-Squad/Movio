package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>

    fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>>

    fun getArtistsByTitle(query: String): Flow<List<ArtistEntity>>

    fun getRecentSearches(): Flow<List<String>>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}