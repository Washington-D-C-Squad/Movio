package com.madrid.data.repositories.remote

import com.madrid.data.CustomHttpClient
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import io.ktor.client.statement.bodyAsText
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val client: CustomHttpClient,
    private val json: Json,
) : RemoteDataSource {

    override suspend fun searchMoviesByQuery(name: String): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/movie"
            parameters.append(QUERY, name)
        }
        val movies = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        return movies
    }

    override suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, name)
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    override suspend fun searchArtistByQuery(name: String): SearchArtistResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/person"
            parameters.append(QUERY, name)
        }
        val artist = json.decodeFromString<SearchArtistResponse>(result.bodyAsText())

        return artist
    }

    override suspend fun getTopRatedMovies(): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/movie/top_rated"
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }

    override suspend fun getTopRatedSeries(): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/tv/top_rated"
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())

        return series
    }
}