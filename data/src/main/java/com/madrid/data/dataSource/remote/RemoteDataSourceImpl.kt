package com.madrid.data.dataSource.remote

import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.MovieDetailsResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.response.series.SeriesDetailsResponse
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import com.madrid.data.repositories.datasource.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.parameters
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json,
) : RemoteDataSource {

    override suspend fun searchMoviesByQuery(name: String): SearchMovieResponse {
        val result = client.get("search/movie")
        parameters { append(QUERY, name) }
        val movies = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        return movies
    }

    override suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse {
        val result = client.get("search/tv")
        parameters { append(QUERY, name) }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    override suspend fun searchArtistByQuery(name: String): SearchArtistResponse {
        val result = client.get("search/person")
        parameters { append(QUERY, name) }
        val artist = json.decodeFromString<SearchArtistResponse>(result.bodyAsText())
        return artist
    }

    override suspend fun getTopRatedMovies(): SearchMovieResponse {
        val result = client.get("movie/top_rated")
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        return movie
    }

    override suspend fun getTopRatedSeries(): HttpResponse {
        return client.get("tv/top_rated")
    }

    override suspend fun getMovieById(movieId: Int): MovieDetailsResponse {
        val result = client.get("movie/$movieId")
        val movie = json.decodeFromString<MovieDetailsResponse>(result.bodyAsText())
        return movie
    }

    override suspend fun getSeriesById(seriesId: Int): SeriesDetailsResponse {
        val result = client.get("tv/$seriesId")
        val series = json.decodeFromString<SeriesDetailsResponse>(result.bodyAsText())
        return series
    }

    override suspend fun getArtistById(artistId: Int): ArtistDetailsResponse {
        val result = client.get("person/$artistId")
        val artist = json.decodeFromString<ArtistDetailsResponse>(result.bodyAsText())
        return artist
    }


}