package com.example.data.repositories

import com.example.data.dataSource.remote.SearchRemoteSource
import com.example.domain.entity.Artist
import com.example.domain.entity.Media
import com.example.domain.entity.Movie
import com.example.domain.entity.RecentSearch
import com.example.domain.entity.Series
import com.example.domain.repository.SearchRepository

class SearchRepository(
    private val searchRemoteSource: SearchRemoteSource
) :SearchRepository {
    override suspend fun getMostSearchedCategory(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getMediaByCategory(category: String): List<Media> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingMedia(): List<Media> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMedia(): List<Media> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieByQuery(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getSeriesByQuery(query: String): List<Series> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtistByQuery(actorName: String): List<Artist> {
        TODO("Not yet implemented")
    }

    override fun getRecentSearches(): List<RecentSearch> {
        TODO("Not yet implemented")
    }

    override fun addRecentSearch(item: RecentSearch) {
        TODO("Not yet implemented")
    }

    override fun removeRecentSearch(id: Int) {
        TODO("Not yet implemented")
    }


    override fun clearAllRecentSearches() {
        TODO("Not yet implemented")
    }


}