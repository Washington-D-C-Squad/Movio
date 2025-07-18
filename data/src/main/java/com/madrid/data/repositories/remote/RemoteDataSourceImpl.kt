package com.madrid.data.repositories.remote

import com.madrid.data.CustomHttpClient
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
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import io.ktor.client.statement.bodyAsText
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val client: CustomHttpClient,
    private val json: Json,
) : RemoteDataSource {

    //Region Movies
    override suspend fun searchMoviesByQuery(name: String): SearchMovieResponse =
        getSearchRequestByQuery<SearchMovieResponse>("/3/search/movie", name)

    override suspend fun getTopRatedMovies(): SearchMovieResponse =
        getRequestByPath<SearchMovieResponse>("/3/movie/top_rated")

    override suspend fun getTopRatedSeries(): SearchSeriesResponse =
        getRequestByPath<SearchSeriesResponse>("/3/tv/top_rated")

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse =
        getRequestByPath<MovieDetailsResponse>("/3/movie/$movieId")

    override suspend fun getMovieTrailersById(movieId: Int): TrailerResponse =
        getRequestByPath<TrailerResponse>("/3/movie/$movieId/videos")

    override suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse =
        getRequestByPath<MovieCreditsResponse>("/3/movie/$movieId/credits")

    override suspend fun getMovieReviewsById(movieId: Int): MovieReviewResponse =
        getRequestByPath<MovieReviewResponse>("/3/movie/$movieId/reviews")

    override suspend fun getSimilarMoviesById(movieId: Int): SimilarMoviesResponse =
        getRequestByPath<SimilarMoviesResponse>("/3/movie/$movieId/similar")
    //End Region

    // Region Series
    override suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse =
        getSearchRequestByQuery<SearchSeriesResponse>("/3/search/tv", name)

    override suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse =
        getRequestByPath<SeriesDetailsResponse>("/3/tv/$seriesId")

    override suspend fun getSeriesTrailersById(seriesId: Int): TrailerResponse =
        getRequestByPath<TrailerResponse>("/3/tv/$seriesId/videos")


    override suspend fun getSeriesCreditsById(seriesId: Int): SeriesCreditResponse =
        getRequestByPath<SeriesCreditResponse>("/3/tv/$seriesId/credits")


    override suspend fun getSeriesReviewsById(seriesId: Int): SeriesReviewResponse =
        getRequestByPath<SeriesReviewResponse>("/3/tv/$seriesId/reviews")


    override suspend fun getSimilarSeriesById(seriesId: Int): SimilarSeriesResponse =
        getRequestByPath<SimilarSeriesResponse>("/3/tv/$seriesId/similar")

    override suspend fun getEpisodesBySeasonId(
        seriesId: Int,
        seasonNumber: Int
    ): SeasonEpisodesResponse =
        getRequestByPath<SeasonEpisodesResponse>("/3/tv/$seriesId/season/$seasonNumber")
    // End Region

    // Region Artist
    override suspend fun searchArtistByQuery(name: String): SearchArtistResponse =
        getSearchRequestByQuery<SearchArtistResponse>("/3/search/person", name)

    override suspend fun getArtistDetailsById(artistId: Int): ArtistDetailsResponse =
        getRequestByPath<ArtistDetailsResponse>("/3/person/$artistId")
    // End Region

    private suspend inline fun <reified T> getRequestByPath(path: String): T {
        val result = client.buildHttpClient { encodedPath = path }
        return json.decodeFromString<T>(result.bodyAsText())
    }

    private suspend inline fun <reified T> getSearchRequestByQuery(path: String, name: String): T {
        val result = client.buildHttpClient {
            encodedPath = path
            parameters.append(QUERY, name)
        }
        return json.decodeFromString<T>(result.bodyAsText())
    }
}