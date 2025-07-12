package com.example.data.dataSource.remote

import com.example.data.dataSource.remote.dto.artists.ArtistApiResponse
import com.example.data.dataSource.remote.dto.multi.MultiApiResponse
import kotlinx.coroutines.flow.Flow

interface SearchRemoteSource {
    suspend fun searchMultiMovieDataByName(name: String, language: String ):Flow<MultiApiResponse>
    suspend fun searchPersonByName(name: String, language: String ) : Flow<ArtistApiResponse>
}