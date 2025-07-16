package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity

interface SearchLocalSource {

    suspend fun getMoviesByTitle(query: String): List<MovieEntity>

    suspend fun getSeriesByTitle(query: String): List<SeriesEntity>

    suspend  fun getArtistsByTitle(query: String): List<ArtistEntity>

    // recent searches
    suspend fun getRecentSearches(): List<RecentSearchEntity>

    suspend fun addRecentSearch(item: RecentSearchEntity)

    suspend fun removeRecentSearch(query: String)

    suspend fun clearAllRecentSearches()

}