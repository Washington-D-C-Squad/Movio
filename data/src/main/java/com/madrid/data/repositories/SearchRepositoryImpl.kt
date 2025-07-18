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


    override suspend fun getMovieByQuery(query: String): List<Movie> {
        var  result = localSource.searchMovieByQueryFromDB(query)
        if (result.isEmpty()) {
            val movie =  remoteDataSource.searchMoviesByQuery(
                name = query,
            ).movieResults?.map {
                it.toMovie()
            }

            movie?.map {
                localSource.insertMovie(it.toMovieEntity())
            }

        }

        return localSource.searchMovieByQueryFromDB(query).map { it.toMovie() }
    }

    override suspend fun getSeriesByQuery(query: String): List<Series> {
        val result = localSource.searchSeriesByQueryFromDB(query)
        if (result.isEmpty()) {
            val remoteData = remoteDataSource.searchSeriesByQuery(
                name = query,
            ).seriesResults?.map {
                it.toSeries()
            }

            remoteData?.map {
                localSource.insertSeries(it.toSeriesEntity())
            }
        }
        return localSource.searchSeriesByQueryFromDB(query).map { it.toSeries() }
    }

    override suspend fun getArtistByQuery(query: String): List<Artist> {
        Log.d("MY_TAG","in get artist in seerch repo imp ".toString())
        val result = localSource.searchArtistByQueryFromDB(query)
        if (result.isEmpty()) {
            val remoteData = remoteDataSource.searchArtistByQuery(
                name = query,
            ).artistResults?.map {
                it.toArtist()
            }
            Log.d("MY_TAG","remot data result $remoteData")

            remoteData?.map {
                Log.d("MY_TAG","remot data $it")
                localSource.insertArtist(it.toArtistEntity())
            }
        }
        Log.d("MY_TAG","after insert ")
        return localSource.searchArtistByQueryFromDB(query).map { it.toArtist() }
    }


    override suspend fun getTopRatedMovies(query: String): List<Movie> {

//        val result = localSource.searchMovieByQueryFromDB(query)
//        if (result.isEmpty()) {
//            remoteDataSource.searchMoviesByQuery(
//                name = query,
//            ).movieResults?.map {
//                it.toMovie()
//            }
//        }
        val res = remoteDataSource.getTopRatedMovies().movieResults?.map {
            it.toMovie()
        } ?: listOf()

        return res
    }

    override suspend fun getTopRatedSeries(query: String): List<Series> {
        var result = localSource.searchSeriesByQueryFromDB(query)
        if (result.isEmpty()) {
            remoteDataSource.searchSeriesByQuery(
                name = query,
            ).seriesResults?.map {
                it.toSeries()
            }
        }

        val res = remoteDataSource.getTopRatedSeries(
        ).seriesResults?.map {
            it.toSeries()
        }?: listOf()

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