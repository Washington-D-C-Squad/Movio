package com.example.data.dataSource.remote

import android.util.Log
import com.example.data.dataSource.remote.dto.multi.MultiApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json


class SearchRemoteDataSource (

): SearchRemoteSource {
    private val client = HttpClient(CIO){
        defaultRequest {
            header("accept", "application/json")
        }
    }

    override suspend fun searchMultiMovieDataByName(
        name: String,
        language: String ,
    ) {

        val res = client.get("https://api.themoviedb.org/3/search/person?language=en-US&page=1&query=moha&api_key=b77ea619291736aea2b7740de4f6bfdc")
        val result = Json.decodeFromString<MultiApiResponse>(res.bodyAsText())
        Log.e("MY_TAG",result.toString())
    }

    override suspend fun searchPersonByName(
        name: String,
        language: String ,
    ) {

    }

}