package com.madrid.data.dataSource.remote.search

import com.madrid.data.BuildConfig.API_KEY
import com.madrid.data.BuildConfig.BASE_URL
import com.madrid.data.BuildConfig.LANGUAGE
import com.madrid.data.BuildConfig.QUERY
import com.madrid.data.dataSource.remote.artists.ArtistApiResponse
import com.madrid.data.dataSource.remote.movies.MovieResponse
import com.madrid.data.dataSource.remote.multiMedia.MultiMediaResponse
import com.madrid.data.dataSource.remote.series.SeriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class SearchApiImpl() : SearchApi {

    private val client = HttpClient(CIO) {
        defaultRequest {
            header("accept", "application/json")
        }
    }
    val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun searchMoviesByName(name: String, language: String): MovieResponse {
        val result = client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "/3/search/movie"
                parameters.append("page", "1")
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append(API_KEY, API_KEY)
            }
        }
        return json.decodeFromString<MovieResponse>(result.bodyAsText())
    }

    override suspend fun searchSeriesByName(name: String, language: String): SeriesResponse {
        val result = client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "search/tv"
                parameters.append(API_KEY, API_KEY)
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append("include_adult", "false")
            }
        }
        return json.decodeFromString<SeriesResponse>(result.bodyAsText())
    }

    override suspend fun searchArtistByName(name: String, language: String): ArtistApiResponse {
        val result = client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "search/person"
                parameters.append(API_KEY, API_KEY)
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
            }
        }
        return json.decodeFromString<ArtistApiResponse>(result.bodyAsText())
    }

    override suspend fun searchMultiMediaByName(
        name: String,
        language: String
    ): MultiMediaResponse {
        val result = client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "/3/search/movie"
                parameters.append("page", "1")
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append(API_KEY, API_KEY)
            }
        }
        return json.decodeFromString<MultiMediaResponse>(result.bodyAsText())
    }
}
