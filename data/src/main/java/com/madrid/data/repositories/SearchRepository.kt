package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.search.SearchApi
import com.madrid.domain.RecentSearchItem
import com.madrid.domain.RecentSearchRepository
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.interfaces.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepository(
    private val multiMediaApi: SearchApi
) : RecentSearchRepository, SearchRepository {
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

    override suspend fun getSearchedMovie(query: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedSeries(query: String): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedArtists(query: String): List<Artist> {
        TODO("Not yet implemented")
    }

    override fun getRecentSearches(): List<RecentSearchItem> {
        return emptyList()
    }

    override fun addRecentSearch(item: RecentSearchItem) {
        TODO("Not yet implemented")
    }

    override fun removeRecentSearch(id: String) {
        TODO("Not yet implemented")
    }

    override fun clearAllRecentSearches() {
        TODO("Not yet implemented")
    }
}