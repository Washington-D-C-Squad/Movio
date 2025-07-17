package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.response.ArtistApiResponse
import com.madrid.data.dataSource.remote.response.MovieResponse
import com.madrid.data.dataSource.remote.response.MultiMediaResponse
import com.madrid.data.dataSource.remote.response.SeriesResponse
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series

interface SearchRemoteSource {


    suspend fun searchMoviesByName(name: String, language: String): MovieResponse

    suspend fun searchSeriesByName(name: String, language: String): SeriesResponse

    suspend fun searchArtistByName(name: String, language: String): ArtistApiResponse

    suspend fun searchMultiMediaByName(name: String, language: String): MultiMediaResponse

    suspend fun getTopRatedMovies(language: String , page: Int): MovieResponse

    suspend fun getTopRatedSeries(language: String , page: Int): SeriesResponse

}