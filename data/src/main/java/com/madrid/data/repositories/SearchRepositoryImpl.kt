package com.madrid.data.repositories

import android.util.Log
import com.madrid.data.dataSource.local.mappers.toArtist
import com.madrid.data.dataSource.local.mappers.toArtistEntity
import com.madrid.data.dataSource.local.mappers.toMovie
import com.madrid.data.dataSource.local.mappers.toMovieEntity
import com.madrid.data.dataSource.local.mappers.toSeries
import com.madrid.data.dataSource.local.mappers.toSeriesEntity
import com.madrid.data.dataSource.remote.mapper.toArtist
import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.dataSource.remote.mapper.toSeries
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class SearchRepositoryImpl(
    private val searchRemoteSource: SearchRemoteSource,
    private val localSource: SearchLocalSource
) : SearchRepository {

    override suspend fun getMovieByQuery(query: String): Flow<List<Movie>> {
        val localMoviesEntities = localSource.getMoviesByTitle(query)
        val isCacheEmpty: Boolean = localMoviesEntities.first().isEmpty()
        if (isCacheEmpty) {
            val result = searchRemoteSource.searchMoviesByName(
                name = query,
                language = "en-US"
            ).movieResults.map {
                it.toMovie()
            }
            // Cache the result locally
            result.map { it.toMovieEntity() }.map {
                localSource.insertMovie(it)
            }
            return flow {
                emit(
                    result
                )
            }
        } else {
            return localMoviesEntities
                .map { entitiesList ->
                    entitiesList.map { it.toMovie() }
                }
        }
    }

    override suspend fun getSeriesByQuery(query: String): Flow<List<Series>> {
        val localSeriesEntities = localSource.getSeriesByTitle(query)
        val isCacheEmpty: Boolean = localSeriesEntities.first().isEmpty()
        if (isCacheEmpty) {
            val result = searchRemoteSource.searchSeriesByName(
                name = query,
                language = "en-US"
            ).seriesResults.map { it.toSeries() }
            // Cache the result locally
            result.map { it.toSeriesEntity() }
                .map { localSource.insertSeries(it) }
            return flow { emit(result) }
        } else {
            return localSeriesEntities
                .map { entitiesList -> entitiesList.map { it.toSeries() } }
        }

    }

    override suspend fun getArtistByQuery(query: String): Flow<List<Artist>> {
        val localArtistEntities = localSource.getArtistsByName(query)
        val isCacheEmpty: Boolean = localArtistEntities.first().isEmpty()
        if (isCacheEmpty) {
            val result = searchRemoteSource.searchArtistByName(
                name = query,
                language = "en-US"
            ).artistResults.map { it.toArtist() }
            // Cache the result locally
            result.map { it.toArtistEntity() }
                .map { localSource.insertArtist(it) }
            return flow {
                emit(
                    result
                )
            }
        } else {
            return localArtistEntities
                .map { entitiesList ->
                    entitiesList.map { it.toArtist() }
                }
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