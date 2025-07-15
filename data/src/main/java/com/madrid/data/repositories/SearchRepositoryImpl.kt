package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.mapper.toArtist
import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.dataSource.remote.mapper.toSeries
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val searchRemoteSource: SearchRemoteSource,
    private val localSource: SearchLocalSource
) : SearchRepository {

    override suspend fun getMovieByQuery(query: String): Flow<List<Movie>> {
        val result = searchRemoteSource.searchMoviesByName(
            name = query,
            language = "en-US"
        ).movieResults.map {
            it.toMovie()
        }
        return flow {
            emit(
               result
            )
        }
    }

    override suspend fun getSeriesByQuery(query: String): Flow<List<Series>> {
        return flow {
            emit(
                searchRemoteSource.searchSeriesByName(
                    name = query,
                    language = "en-US"
                ).seriesResults.map {
                    it.toSeries()
                }
            )
        }
    }

    override suspend fun getArtistByQuery(query: String): Flow<List<Artist>> {
        return flow {
            emit(
                searchRemoteSource.searchArtistByName(
                    name = query,
                    language = "en-US"
                ).artistResults.map {
                    it.toArtist()
                }
            )
        }
    }

    override suspend fun getMediaByQuery(query: String): Flow<List<Media>> {
        TODO("Not yet implemented")
    }


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