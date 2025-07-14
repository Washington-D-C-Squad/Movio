package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.response.ArtistApiResponse
import com.madrid.data.dataSource.remote.response.MovieResponse
import com.madrid.data.dataSource.remote.response.MultiMediaResponse
import com.madrid.data.dataSource.remote.response.SeriesResponse

interface SearchRemoteSource {


    suspend fun searchMoviesByName (name: String,language: String ) : MovieResponse

    suspend fun searchSeriesByName(name: String, language: String): SeriesResponse

    suspend fun searchArtistByName(name: String, language: String): ArtistApiResponse

    suspend fun searchMultiMediaByName(name: String, language: String): MultiMediaResponse

}