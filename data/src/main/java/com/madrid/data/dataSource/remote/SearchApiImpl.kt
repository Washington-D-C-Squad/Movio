package com.madrid.data.dataSource.remote.stache

import com.madrid.data.dataSource.remote.movieResponse.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class SearchApiImpl(


) : SearchApi {
    private val client = HttpClient(CIO) {
        defaultRequest {
            header("accept", "application/json")
        }
    }
    val json = Json {
        ignoreUnknownKeys = true
    }

    // this the correct call
    override suspend fun searchMoviesByName(name: String, language: String): MovieResponse{

        val res = client.get {
            url {
                protocol =HTTPS
                host = BASE_URL
                encodedPath = "/3/search/movie"
                parameters.append("page", "1")
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append(API_KEY, KEY)
            }
        }

        return json.decodeFromString<MovieResponse>(res.bodyAsText())
    }

    override suspend fun searchSeriesByName(name: String, language: String): HttpResponse {
        return client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "search/tv"
                parameters.append(API_KEY, KEY)
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
                parameters.append("include_adult", "false")
            }
        }
    }

    override suspend fun searchArtistByName(name: String, language: String): HttpResponse {
        return client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "search/person"
                parameters.append(API_KEY, KEY)
                parameters.append(LANGUAGE, language)
                parameters.append(QUERY, name)
            }
        }
    }

    companion object {
        //TO BE MOVE INTO BUILD CONFIG LATER
        const val BASE_URL = "api.themoviedb.org"
        const val API_KEY = "api_key"
        const val KEY = "b77ea619291736aea2b7740de4f6bfdc"
        const val LANGUAGE = "language"
        const val QUERY = "query"
    }
}