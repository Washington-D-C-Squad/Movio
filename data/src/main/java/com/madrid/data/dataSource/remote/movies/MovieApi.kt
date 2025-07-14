package com.madrid.data.dataSource.remote.movies

import io.ktor.client.statement.HttpResponse


interface MoviesApi {

    suspend fun getTopRatedMovies(language: String, page: Int): HttpResponse

}