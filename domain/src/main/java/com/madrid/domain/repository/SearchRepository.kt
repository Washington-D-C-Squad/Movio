package com.madrid.domain.repository

import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getMovieByQuery(query: String): List<Movie>
    suspend fun getSeriesByQuery(query: String): List<Series>
    suspend fun getArtistByQuery(query: String): List<Artist>

    suspend fun getMostSearchedCategories(): List<String>
    suspend fun getMediaByCategory(category: String): Media

    suspend fun getTrendingMedia(): Media

    suspend fun getTopRatedMovies(language: String): Flow<List<Movie>>
    suspend fun getTopRatedSeries(language: String): Flow<List<Series>>


    suspend fun getMediaByQuery(query: String): Flow<List<Media>>

    suspend fun getRecentSearches(): List<String>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()
}