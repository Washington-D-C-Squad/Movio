package com.madrid.data.repositories.remote

import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.common.TrailerResponse
import com.madrid.data.dataSource.remote.response.movie.MovieCreditsResponse
import com.madrid.data.dataSource.remote.response.movie.MovieDetailsResponse
import com.madrid.data.dataSource.remote.response.movie.MovieReviewResponse
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.dataSource.remote.response.movie.SimilarMoviesResponse
import com.madrid.data.dataSource.remote.response.series.SearchSeriesResponse
import com.madrid.data.dataSource.remote.response.series.SeasonEpisodesResponse
import com.madrid.data.dataSource.remote.response.series.SeriesCreditResponse
import com.madrid.data.dataSource.remote.response.series.SeriesDetailsResponse
import com.madrid.data.dataSource.remote.response.series.SeriesReviewResponse
import com.madrid.data.dataSource.remote.response.series.SimilarSeriesResponse

interface RemoteDataSource {

    // Region Movies
    suspend fun searchMoviesByQuery(name: String): SearchMovieResponse
    suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse
    suspend fun getMovieTrailersById(movieId: Int): TrailerResponse
    suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse
    suspend fun getMovieReviewsById(movieId: Int): MovieReviewResponse
    suspend fun getSimilarMoviesById(movieId: Int): SimilarMoviesResponse
    // End Region

    // Region Series
    suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse
    suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse
    suspend fun getSeriesTrailersById(seriesId: Int): TrailerResponse
    suspend fun getSeriesCreditsById(seriesId: Int): SeriesCreditResponse
    suspend fun getSeriesReviewsById(seriesId: Int): SeriesReviewResponse
    suspend fun getSimilarSeriesById(seriesId: Int): SimilarSeriesResponse
    suspend fun getEpisodesBySeasonId(seriesId: Int, seasonNumber: Int): SeasonEpisodesResponse
    // End Region

    //Region Artist
    suspend fun searchArtistByQuery(name: String): SearchArtistResponse
    suspend fun getArtistDetailsById(artistId: Int): ArtistDetailsResponse
    //End Region

    suspend fun getTopRatedMovies(): SearchMovieResponse
    suspend fun getTopRatedSeries(): SearchSeriesResponse
}