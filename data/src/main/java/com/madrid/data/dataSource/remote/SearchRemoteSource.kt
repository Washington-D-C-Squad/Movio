package com.madrid.data.dataSource.remote

import com.madrid.data.dataSource.remote.dto.artists.ArtistApiResponse
import com.madrid.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRemoteSource {
    suspend fun searchMultiMovieDataByName(name: String, language: String ):Flow<List<Movie>>
    suspend fun searchPersonByName(name: String, language: String ) : Flow<ArtistApiResponse>
}