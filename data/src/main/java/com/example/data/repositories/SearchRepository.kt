package com.example.data.repositories

import com.example.domain.RecentSearchRepository
import com.example.domain.RecentSearchItem
import com.example.domain.entity.Artist
import com.example.domain.entity.Movie
import com.example.domain.entity.Series
import com.example.domain.interfaces.SearchRepository

class SearchRepository (

) : RecentSearchRepository , SearchRepository
{
    override suspend fun getRecentSearches(): List<RecentSearchItem> {
        // TODO: Implement actual data source logic
        return emptyList()
    }

    override suspend fun addRecentSearch(item: RecentSearchItem) {
        // TODO: Implement actual data source logic
    }

    override suspend fun removeRecentSearch(item: RecentSearchItem) {
        // TODO: Implement actual data source logic
    }

    override suspend fun clearAllRecentSearches() {
        // TODO: Implement actual data source logic
    }

    override suspend fun getAllMovie(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSeries(): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllArtist(): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovie(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedSeries(): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopResults(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedMovie(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedSeries(query: String): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedArtists(query: String): List<Artist> {
        TODO("Not yet implemented")
    }
}