package com.madrid.data.repositories.datasource

import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.MovieDetailsResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.response.series.SeriesDetailsResponse
import io.ktor.client.statement.HttpResponse

interface RemoteDataSource {

    suspend fun searchMoviesByQuery(name: String): SearchMovieResponse
    suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse
    suspend fun searchArtistByQuery(name: String): SearchArtistResponse

    suspend fun getTopRatedMovies(): SearchMovieResponse
    suspend fun getTopRatedSeries(): HttpResponse

    suspend fun getMovieById(movieId: Int): MovieDetailsResponse
    suspend fun getSeriesById(seriesId: Int): SeriesDetailsResponse
    suspend fun getArtistById(artistId: Int): ArtistDetailsResponse

}