package com.madrid.domain.repository

import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series

interface SearchRepository {
    suspend fun getMovieByQuery(query: String): List<Movie>
    suspend fun getSeriesByQuery(query: String): List<Series>
    suspend fun getArtistByQuery(query: String): List<Artist>
    suspend fun getRecommendedMovie(): List<Movie>
    suspend fun getPopularMovie(): List<Movie>
    suspend fun getRecentSearches(): List<String>
    suspend fun addRecentSearchByQuery(query: String)
    suspend fun removeRecentSearchByQuery(query: String)
    suspend fun clearAllRecentSearches()
}