package com.madrid.data.repositories.remote

import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import io.ktor.client.statement.HttpResponse

interface RemoteDataSource {

    suspend fun searchMoviesByQuery(name: String): SearchMovieResponse
    suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse
    suspend fun searchArtistByQuery(name: String): SearchArtistResponse

    suspend fun getTopRatedMovies(): SearchMovieResponse
    suspend fun getTopRatedSeries(): HttpResponse
}