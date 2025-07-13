package com.example.data.repositories

import com.example.data.dataSource.remote.SearchRemoteSource
import com.example.domain.entity.Artist
import com.example.domain.entity.Media
import com.example.domain.entity.Movie
import com.example.domain.entity.Series
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepository(
    private val searchRemoteSource: SearchRemoteSource
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


    override suspend fun getRecentSearches(): List<String> {
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