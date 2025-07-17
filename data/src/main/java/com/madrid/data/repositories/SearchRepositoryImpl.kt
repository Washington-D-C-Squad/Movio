package com.madrid.data.repositories

import com.madrid.data.dataSource.local.mappers.toArtist
import com.madrid.data.dataSource.local.mappers.toMovie
import com.madrid.data.dataSource.local.mappers.toSeries
import com.madrid.data.dataSource.remote.mapper.toArtist
import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.dataSource.remote.mapper.toSeries
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(

    private val remoteDataSource: RemoteDataSource,
    private val localSource: LocalDataSource
) : SearchRepository {


    override suspend fun getMovieByQuery(query: String): List<Movie> {
        val result = localSource.searchMovieByQueryFromDB(query)
        if (result.isEmpty()) {
            remoteDataSource.searchMoviesByQuery(
                name = query,
            ).movieResults?.map {
                it.toMovie()
            }
        }
        return localSource.searchMovieByQueryFromDB(query).map { it.toMovie() }
    }

    override suspend fun getSeriesByQuery(query: String): List<Series> {
        val result = localSource.searchSeriesByQueryFromDB(query)
        if (result.isEmpty()) {
            remoteDataSource.searchSeriesByQuery(
                name = query,
            ).seriesResults?.map {
                it.toSeries()
            }
        }
        return localSource.searchSeriesByQueryFromDB(query).map { it.toSeries() }
    }

    override suspend fun getArtistByQuery(query: String): List<Artist> {
        val result = localSource.searchArtistByQueryFromDB(query)
        if (result.isEmpty()) {
            remoteDataSource.searchArtistByQuery(
                name = query,
            ).artistResults?.map {
                it.toArtist()
            }
        }
        return localSource.searchArtistByQueryFromDB(query).map { it.toArtist() }
    }


    override suspend fun getTopRatedMovies(query: String): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedSeries(query: String): List<Series> {
        TODO("Not yet implemented")
    }


    override suspend fun getRecommendedMovie(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovie(): List<Movie> {
        TODO("Not yet implemented")
    }


    override suspend fun getRecentSearches(): Flow<List<String>> {
        return localSource.getRecentSearches()
    }

    override suspend fun addRecentSearchByQuery(item: String) {
        localSource.addRecentSearch(item)
    }

    override suspend fun removeRecentSearchByQuery(item: String) {
        localSource.removeRecentSearch(item)
    }

    override suspend fun clearAllRecentSearches() {
        localSource.clearAllRecentSearches()
    }
}