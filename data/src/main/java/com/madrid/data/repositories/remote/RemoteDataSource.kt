package com.madrid.data.repositories.remote

import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse

interface RemoteDataSource {

    suspend fun searchMoviesByQuery(
        name: String,
        page: Int
    ): SearchMovieResponse

    suspend fun searchSeriesByQuery(
        name: String,
        page: Int
    ): SearchSeriesResponse

    suspend fun searchArtistByQuery(
        name: String,
        page: Int
    ): SearchArtistResponse

    suspend fun getTopRatedMovies(
        query: String,
        page: Int

    ): SearchMovieResponse

    suspend fun getTopRatedSeries(
        query: String,
        page: Int

    ): SearchSeriesResponse
}