package com.madrid.data.dataSource.remote.stache

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath

class MoviesApiImpl(

    private val client: HttpClient
) : MoviesApi {


    override suspend fun getTopRatedMovies(language: String, page: Int): HttpResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "https://api.themoviedb.org/3/"
                encodedPath = "movie/top_rated"
            }
        }
    }
}