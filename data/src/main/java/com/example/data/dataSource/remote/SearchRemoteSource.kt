package com.example.data.dataSource.remote

import com.example.data.dataSource.remote.dto.artists.ArtistApiResponse
import com.example.data.dataSource.remote.dto.multi.MultiApiResponse
import com.example.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRemoteSource {
    suspend fun searchMultiMovieDataByName(name: String, language: String ):Flow<List<Movie>>
    suspend fun searchPersonByName(name: String, language: String ) : Flow<ArtistApiResponse>
}