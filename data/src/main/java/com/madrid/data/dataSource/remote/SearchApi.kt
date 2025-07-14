package com.madrid.data.dataSource.remote.stache

import com.madrid.data.dataSource.remote.movieResponse.MovieResponse
import io.ktor.client.statement.HttpResponse

interface SearchApi {


    suspend fun searchMoviesByName (name: String,language: String ) : MovieResponse

    suspend fun searchSeriesByName (name: String,language: String ) : HttpResponse

    suspend fun searchArtistByName (name: String,language: String ) : HttpResponse

}