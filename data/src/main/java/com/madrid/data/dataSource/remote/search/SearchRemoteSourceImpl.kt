package com.madrid.data.dataSource.remote.search

import com.madrid.data.BuildConfig.API_KEY
import com.madrid.data.BuildConfig.BASE_URL
import com.madrid.data.dataSource.remote.response.ArtistApiResponse
import com.madrid.data.dataSource.remote.response.MovieResponse
import com.madrid.data.dataSource.remote.response.MultiMediaResponse
import com.madrid.data.dataSource.remote.response.SeriesResponse
import com.madrid.data.dataSource.remote.utils.Constants.KEY
import com.madrid.data.dataSource.remote.utils.Constants.LANGUAGE
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import com.madrid.data.repositories.SearchRemoteSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class SearchRemoteSourceImpl() : SearchRemoteSource {

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
                parameters.append( PAGE, "1")
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append(KEY, API_KEY)
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
                parameters.append(KEY, API_KEY)
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
                parameters.append(KEY, API_KEY)
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
                parameters.append(PAGE, "1")
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append(KEY, API_KEY)
            }
        }
        return json.decodeFromString<MultiMediaResponse>(result.bodyAsText())
    }
}
