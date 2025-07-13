package com.example.domain.repository

import com.example.domain.entity.Artist
import com.example.domain.entity.Media
import com.example.domain.entity.Movie
import com.example.domain.entity.RecentSearch
import com.example.domain.entity.Series

interface SearchRepository {
    suspend fun getMostSearchedCategory(): List<String>
    suspend fun getMediaByCategory(category: String): List<Media>

    suspend fun getTrendingMedia(): List<Media>

    suspend fun getTopRatedMedia(): List<Media>
    suspend fun getMovieByQuery(query: String): List<Movie>
    suspend fun getSeriesByQuery(query: String): List<Series>
    suspend fun getArtistByQuery(actorName: String): List<Artist>

    fun getRecentSearches(): List<RecentSearch>
    fun addRecentSearch(item: RecentSearch)
    fun removeRecentSearch(id: Int)
    fun clearAllRecentSearches()
}