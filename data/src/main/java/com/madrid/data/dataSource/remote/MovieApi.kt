package com.madrid.data.dataSource.remote.stache

import io.ktor.client.statement.HttpResponse


interface MoviesApi {

    suspend fun getTopRatedMovies(language: String, page: Int): HttpResponse

}