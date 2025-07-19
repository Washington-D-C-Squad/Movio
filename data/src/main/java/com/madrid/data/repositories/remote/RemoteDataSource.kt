package com.madrid.data.repositories.remote

import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.movie.MovieCreditsResponse
import com.madrid.data.dataSource.remote.response.movie.MovieDetailsResponse
import com.madrid.data.dataSource.remote.response.movie.MovieReviewResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.movie.SimilarMoviesResponse
import com.madrid.data.dataSource.remote.response.movie.TrailerResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.response.series.SeriesDetailsResponse

interface RemoteDataSource {

    suspend fun searchMoviesByQuery(
        name: String,
        page: Int
    ): SearchMovieResponse

    suspend fun searchSeriesByQuery(
        name: String,
        page: Int
    ): SearchSeriesResponse

    suspend fun searchArtistByQuery(
        name: String,
        page: Int
    ): SearchArtistResponse

    suspend fun getTopRatedMovies(
        query: String,
        page: Int

    ): SearchMovieResponse

    suspend fun getTopRatedMovies(
        page: Int
    ): SearchMovieResponse


    suspend fun getTopRatedSeries(
        query: String,
        page: Int
    ): SearchSeriesResponse

    suspend fun getPopularMovie(
        page: Int
    ): SearchMovieResponse


    suspend fun getTopRatedMovies(): SearchMovieResponse
    suspend fun getTopRatedSeries(): SearchSeriesResponse


    // region movie details
    suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse
    suspend fun getMovieTrailersById(movieId: Int): TrailerResponse
    suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse
    suspend fun getMovieReviewsById(movieId:Int) : MovieReviewResponse
    suspend fun getSimilarMoviesById(movieId:Int) : SimilarMoviesResponse
    // endregion


    suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse

    suspend fun getArtistById(artistId: Int): ArtistDetailsResponse



}