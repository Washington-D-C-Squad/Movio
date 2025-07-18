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
import com.madrid.domain.entity.Movie

interface RemoteDataSource {

    suspend fun searchMoviesByQuery(name: String): SearchMovieResponse
    suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse
    suspend fun searchArtistByQuery(name: String): SearchArtistResponse

    suspend fun getTopRatedMovies(): SearchMovieResponse
    suspend fun getTopRatedSeries(): SearchSeriesResponse


    // region movie details
    suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse
    suspend fun getMovieTrailers(movieId: Int): TrailerResponse
    suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse
    suspend fun getMovieReviewsById(movieId:Int) : MovieReviewResponse
    suspend fun getSimilarMoviesById(movieId:Int) : SimilarMoviesResponse
    // endregion


    suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse

    suspend fun getArtistById(artistId: Int): ArtistDetailsResponse



}