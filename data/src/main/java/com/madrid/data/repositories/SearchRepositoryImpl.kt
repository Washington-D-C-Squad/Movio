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
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localSource: LocalDataSource
) : SearchRepository {


    override suspend fun getMovieByQuery(query: String, page: Int): List<Movie> {
//        val result = localSource.searchMovieByQueryFromDB(query)
//        return if (result.isEmpty()) {
//            val movie = remoteDataSource.searchMoviesByQuery(
//                name = query,
//                page = page
//
//            ).movieResults?.map {
//                it.toMovie()
//            }
//
//            movie?.map {
//                localSource.insertMovie(it.toMovieEntity())
//            }
//            localSource.searchMovieByQueryFromDB(query).map { it.toMovie() }
//        } else {
//            result.map {
//                it.toMovie()
//            }
//        }
//
        val res = remoteDataSource.getTopRatedMovies(
            query = query,
            page = page
        ).movieResults?.map {
            it.toMovie()
        } ?: listOf()

        return res

    }

    override suspend fun getSeriesByQuery(query: String, page: Int): List<Series> {
        val result = localSource.searchSeriesByQueryFromDB(query)
        if (result.isEmpty()) {
            val remoteData = remoteDataSource.searchSeriesByQuery(
                name = query,
                page = page
            ).seriesResults?.map {
                it.toSeries()
            }

            remoteData?.map {
                localSource.insertSeries(it.toSeriesEntity())
            }
        }
        return localSource.searchSeriesByQueryFromDB(query).map { it.toSeries() }
    }

    override suspend fun getArtistByQuery(query: String, page: Int): List<Artist> {
//        val result = localSource.searchArtistByQueryFromDB(query)
//        Log.d("in impl", "getArtistByQuery: $result")
//        if (result.isEmpty()) {
//            val remoteData = remoteDataSource.searchArtistByQuery(
//                name = query,
//                page = page
//            ).artistResults?.map {
//                Log.d("in impl", "getArtistByQuery: $it")
//                it.toArtist()
//            }
//            Log.d("in impl", "getArtistByQuery: $remoteData")
//            remoteData?.map {
//                localSource.insertArtist(it.toArtistEntity())
//
//            }
//        }
//
//        return localSource.searchArtistByQueryFromDB(query).map {
//            Log.d("in impl", "getArtistByQuery: $it")
//            it.toArtist()
//        }
        return remoteDataSource.searchArtistByQuery(query, page).artistResults?.map {
            it.toArtist()
        } ?: listOf()
    }


    override suspend fun getTopRatedMovies(query: String, page: Int): List<Movie> {
        val res = remoteDataSource.getTopRatedMovies(
            query = query,
            page = page
        ).movieResults?.map {
            it.toMovie()
        } ?: listOf()

        return res
    }

    override suspend fun getTopRatedSeries(query: String, page: Int): List<Series> {
        val res = remoteDataSource.getTopRatedSeries(
            query = query,
            page = page
        ).seriesResults?.map {
            it.toSeries()
        } ?: listOf()

        return res
    }


    override suspend fun getRecommendedMovie(): List<Movie> {
        return emptyList()
    }

    override suspend fun getPopularMovie(): List<Movie> {
        return emptyList()
    }


    override suspend fun getRecentSearches(): List<String> {
        return localSource.getRecentSearches().map {
            it.searchQuery
        }
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