package com.madrid.data.dataSource.remote.search

import com.madrid.data.dataSource.remote.artists.ArtistApiResponse
import com.madrid.data.dataSource.remote.movies.MovieResponse
import com.madrid.data.dataSource.remote.multiMedia.MultiMediaResponse
import com.madrid.data.dataSource.remote.series.SeriesResponse

interface SearchApi {


    suspend fun searchMoviesByName (name: String,language: String ) : MovieResponse

    suspend fun searchSeriesByName(name: String, language: String): SeriesResponse

    suspend fun searchArtistByName(name: String, language: String): ArtistApiResponse

    suspend fun searchMultiMediaByName(name: String, language: String): MultiMediaResponse

}