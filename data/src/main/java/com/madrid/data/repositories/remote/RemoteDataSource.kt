package com.madrid.data.repositories.remote

import com.madrid.data.dataSource.remote.response.artist.ArtistDetailsResponse
import com.madrid.data.dataSource.remote.response.artist.ArtistKnownForResponse
import com.madrid.data.dataSource.remote.response.artist.SearchArtistResponse
import com.madrid.data.dataSource.remote.response.common.TrailerResponse
import com.madrid.data.dataSource.remote.response.genre.GenresResponse
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

    // Region Series
    suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse
    suspend fun getSeriesTrailersById(seriesId: Int): TrailerResponse
    suspend fun getSeriesCreditsById(seriesId: Int): SeriesCreditResponse
    suspend fun getSeriesReviewsById(seriesId: Int): SeriesReviewResponse
    suspend fun getSimilarSeriesById(seriesId: Int): SimilarSeriesResponse
    suspend fun getEpisodesBySeasonId(seriesId: Int, seasonNumber: Int): SeasonEpisodesResponse
    suspend fun getSeriesGenres(): GenresResponse
    // End Region

    suspend fun getTopRatedMovies(
        page: Int
    ): SearchMovieResponse

    // Region Movies
    suspend fun searchMoviesByQuery(name: String): SearchMovieResponse
    // End Region


    //Region Artist
    suspend fun getArtistDetailsById(artistId: Int): ArtistDetailsResponse
    suspend fun getArtistKnownForById(artistId: Int): ArtistKnownForResponse
    //End Region

    suspend fun getTopRatedSeries(
        query: String,
        page: Int
    ): SearchSeriesResponse

    suspend fun getPopularMovie(
        page: Int
    ): SearchMovieResponse

    // region movie details
    suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse
    suspend fun getMovieTrailersById(movieId: Int): TrailerResponse
    suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse
    suspend fun getMovieReviewsById(movieId:Int) : MovieReviewResponse
    suspend fun getSimilarMoviesById(movieId:Int) : SimilarMoviesResponse
    // endregion


    suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse

    suspend fun getArtistById(artistId: Int): ArtistDetailsResponse



    // region genres
    suspend fun getMovieGenres(): GenresResponse

}