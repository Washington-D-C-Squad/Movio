package com.madrid.data.repositories

import android.util.Log
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
import org.intellij.lang.annotations.Language

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
        val result = searchRemoteSource.searchSeriesByName(
            name = query,
            language = "en-US"
        ).seriesResults.map {
            it.toSeries()
        }
        return flow {
            emit(
                result
            )
        }

    }

    override suspend fun getArtistByQuery(query: String): Flow<List<Artist>> {

        val result = searchRemoteSource.searchArtistByName(
            name = query,
            language = "en-US"
        ).artistResults.map {
            it.toArtist()
        }
        return flow {
            emit(
                result
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
//////////////
    override suspend fun getTrendingMedia(): Media {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(language: String , page: Int): Flow<List<Movie> >{
        val result = searchRemoteSource.getTopRatedMovies(
            language = "en-US",
            page = page
        ).movieResults.map {
            it.toMovie()
        }
        return flow {
            emit(
                result
            )
        }
    }

    override suspend fun getTopRatedSeries(language: String , page: Int):Flow<List<Series>> {
        val result = searchRemoteSource.getTopRatedSeries(
            language = "en-US",
            page = page
        ).seriesResults.map {
            it.toSeries()
        }
        return flow {
            emit(
                result
            )
        }
    }

    override suspend fun getRecentSearches(): Flow<List<String>> {
        return localSource.getRecentSearches()
    }

    override suspend fun addRecentSearch(item: String) {
        localSource.addRecentSearch(item)
    }

    override suspend fun removeRecentSearch(item: String) {
        localSource.removeRecentSearch(item)
    }

    override suspend fun clearAllRecentSearches() {
        localSource.clearAllRecentSearches()
    }
}