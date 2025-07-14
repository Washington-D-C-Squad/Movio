package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.search.SearchApi
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val searchRemoteSource: SearchApi,
    private val localSource: SearchLocalSource
) : SearchRepository {
    override suspend fun getMostSearchedCategories(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getMediaByCategory(category: String): Media {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingMedia(): Media {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedSeries(query: String): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByQuery(query: String): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSeriesByQuery(query: String): Flow<List<Series>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtistByQuery(query: String): Flow<List<Artist>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecentSearches(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addRecentSearch(item: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeRecentSearch(item: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllRecentSearches() {
        TODO("Not yet implemented")
    }


}