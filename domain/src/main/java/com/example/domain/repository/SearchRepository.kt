package com.example.domain.repository

import com.example.domain.entity.Artist
import com.example.domain.entity.Media
import com.example.domain.entity.Movie
import com.example.domain.entity.RecentSearch
import com.example.domain.entity.Series
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getMostSearchedCategories(): List<String>
    suspend fun getMediaByCategory(category: String): List<Media>

    suspend fun getTrendingMedia(): List<Media>

    suspend fun getTopRatedMedia(): List<Media>
    suspend fun getMovieByQuery(query: String): Flow<List<Movie>>
    suspend fun getSeriesByQuery(query: String): Flow<List<Series>>
    suspend fun getArtistByQuery(query: String): Flow<List<Artist>>

    fun getRecentSearches(): List<RecentSearch>
    fun addRecentSearch(item: RecentSearch)
    fun removeRecentSearch(id: Int)
    fun clearAllRecentSearches()
}