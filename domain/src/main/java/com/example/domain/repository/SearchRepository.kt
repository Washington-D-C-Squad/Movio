package com.example.domain.repository

import com.example.domain.entity.Artist
import com.example.domain.entity.Media
import com.example.domain.entity.Movie
import com.example.domain.entity.RecentSearch
import com.example.domain.entity.Series
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getMostSearchedCategories(): List<String>
    suspend fun getMediaByCategory(category: String): Media

    suspend fun getTrendingMedia(): Media

    suspend fun getTopRatedMovies(query: String): List<Movie>
    suspend fun getTopRatedSeries(query: String): List<Series>

    suspend fun getMovieByQuery(query: String): Flow<List<Movie>>
    suspend fun getSeriesByQuery(query: String): Flow<List<Series>>
    suspend fun getArtistByQuery(query: String): Flow<List<Artist>>

    suspend fun getRecentSearches(): List<String>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}