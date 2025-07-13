package com.example.data.repositories

import com.example.data.dataSource.remote.SearchRemoteSource
import com.example.domain.RecentSearchItem
import com.example.domain.RecentSearchRepository
import com.example.domain.entity.Artist
import com.example.domain.entity.Movie
import com.example.domain.entity.Series
import com.example.domain.interfaces.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepository(
    private val searchRemoteSource: SearchRemoteSource
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
        return searchRemoteSource.searchMultiMovieDataByName(
            name = query,
            language = "en-US"
        )
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