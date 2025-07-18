package com.madrid.data.repositories.remote

import android.util.Log
import com.madrid.data.CustomHttpClient
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import io.ktor.client.statement.bodyAsText
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val client: CustomHttpClient,
    private val json: Json,
) : RemoteDataSource {

    override suspend fun searchMoviesByQuery(
        name: String,
        page: Int
    ): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/movie"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val movies = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        Log.d("in impl", "searchMoviesByQuery: $movies")
        return movies
    }

    override suspend fun searchSeriesByQuery(
        name: String,
        page: Int
    ): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    override suspend fun searchArtistByQuery(
        name: String,
        page: Int
    ): SearchArtistResponse {
        Log.d("KTOR", "searchArtistByQuery: $name")
        val result = client.buildHttpClient {
            encodedPath = "/3/search/person"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val artist = json.decodeFromString<SearchArtistResponse>(result.bodyAsText())

        return artist
    }

    override suspend fun getTopRatedMovies(
        query: String,
        page: Int
    ): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/movie"
            parameters.append(QUERY, query)
            parameters.append(PAGE, page.toString())
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }

    override suspend fun getTopRatedSeries(
        query: String,
        page: Int
    ): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, query)
            parameters.append(PAGE, page.toString())
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())

        return series
    }
}