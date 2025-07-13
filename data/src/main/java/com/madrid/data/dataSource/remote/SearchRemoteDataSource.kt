package com.madrid.data.dataSource.remote

import com.madrid.data.dataSource.remote.dto.artists.ArtistApiResponse
import com.madrid.data.dataSource.remote.dto.multi.MultiApiResponse
import com.madrid.domain.entity.Movie
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    ): Flow<List<Movie>>{
        val res = client
            .get(
            "https://api.themoviedb.org/3/search/person?language=$language&page=1&query=$name&api_key=b77ea619291736aea2b7740de4f6bfdc"
            )
        val json = Json {
            ignoreUnknownKeys = true
        }
        return flow {
            emit(
                json.decodeFromString<MultiApiResponse>(res.bodyAsText()).results.map {
                    Movie(
                        title = it.name,
                        imageUrl =" https://image.tmdb.org/t/p/w500$it.profilePath",
                        rate = it.popularity
                    )
                }
            )
        }

    }

    override suspend fun searchPersonByName(
        name: String,
        language: String
    ): Flow<ArtistApiResponse> {
        val res = client
            .get(
                "https://api.themoviedb.org/3/search/person?language=$language&page=1&query=$name&api_key=b77ea619291736aea2b7740de4f6bfdc"
            )
        val json = Json {
            ignoreUnknownKeys = true
        }
        return flow {
            emit(
                json.decodeFromString<ArtistApiResponse>(res.bodyAsText())
            )
        }
    }

}