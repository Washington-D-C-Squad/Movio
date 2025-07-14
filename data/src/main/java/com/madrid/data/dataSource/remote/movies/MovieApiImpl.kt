package com.madrid.data.dataSource.remote.movies

import com.madrid.data.BuildConfig.API_KEY
import com.madrid.data.BuildConfig.BASE_URL
import com.madrid.data.dataSource.remote.utils.Constants.KEY
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
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

class MoviesApiImpl() : MoviesApi {

    private val client = HttpClient(CIO) {
        defaultRequest {
            header("accept", "application/json")
        }
    }
    val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getTopRatedMovies(language: String, page: Int): HttpResponse {
        val result = client.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                encodedPath = "movie/top_rated"
                parameters.append(PAGE, "1")
                parameters.append(KEY, API_KEY)
            }
        }
        return json.decodeFromString<HttpResponse>(result.bodyAsText())
    }
}

