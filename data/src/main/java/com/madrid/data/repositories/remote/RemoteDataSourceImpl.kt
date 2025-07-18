package com.madrid.data.repositories.remote

import android.util.Log
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
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
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

     suspend fun getTopRatedMovies(): SearchMovieResponse =
        getRequestByPath<SearchMovieResponse>("/3/movie/top_rated")

     suspend fun getTopRatedSeries(): SearchSeriesResponse =
        getRequestByPath<SearchSeriesResponse>("/3/tv/top_rated")


    // Region Series
    override suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse =
        getSearchRequestByQuery<SearchSeriesResponse>("/3/search/tv", name)

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
    suspend fun searchArtistByQuery(name: String): SearchArtistResponse =
        getSearchRequestByQuery<SearchArtistResponse>("/3/search/person", name)

    override suspend fun getArtistDetailsById(artistId: Int): ArtistDetailsResponse =
        getRequestByPath<ArtistDetailsResponse>("/3/person/$artistId")
    // End Region





    override suspend fun searchMoviesByQuery(
        name: String,
        page: Int
    ): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/movie"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val movies = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        Log.d("in impl", "searchMoviesByQuery: $movies")
        return movies
    }

    override suspend fun searchSeriesByQuery(
        name: String,
        page: Int
    ): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    private suspend inline fun <reified T> getRequestByPath(path: String): T {
        val result = client.buildHttpClient { encodedPath = path }
        return json.decodeFromString<T>(result.bodyAsText())
    }

    override suspend fun searchArtistByQuery(
        name: String,
        page: Int
    ): SearchArtistResponse {
        Log.d("KTOR", "searchArtistByQuery: $name")
        val result = client.buildHttpClient {
            encodedPath = "/3/search/person"
            parameters.append(QUERY, name)
            parameters.append(PAGE, page.toString())
        }
        val artist = json.decodeFromString<SearchArtistResponse>(result.bodyAsText())

        return artist
    }

    private suspend inline fun <reified T> getSearchRequestByQuery(path: String, name: String): T {
        val result = client.buildHttpClient {
            encodedPath = path
            parameters.append(QUERY, name)
        }
        return json.decodeFromString<T>(result.bodyAsText())
    }
    override suspend fun getTopRatedMovies(
        query: String,
        page: Int
    ): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "3/search/movie"
            parameters.append(QUERY, query)
            parameters.append(PAGE, page.toString())
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }

    override suspend fun getTopRatedMovies(
        page: Int
    ): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "3/movie/top_rated"
            parameters.append(PAGE, page.toString())
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }

    override suspend fun getTopRatedSeries(
        query: String,
        page: Int
    ): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, query)
            parameters.append(PAGE, page.toString())
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())

        return series
    }

    override suspend fun getPopularMovie(page: Int): SearchMovieResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/movie/popular"
            parameters.append(PAGE, page.toString())
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }



    override suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/movie/$movieId"

        }
        Log.e("MY_TAG"," middile  ${result.bodyAsText()}")

        val movie = json.decodeFromString<MovieDetailsResponse>(result.bodyAsText())
        Log.e("MY_TAG"," movie  ${result.bodyAsText()}")

        return movie
    }

    override suspend fun getMovieTrailersById(movieId: Int): TrailerResponse {
        val result = client.buildHttpClient { encodedPath = "/3/movie/$movieId/videos" }
        val trailer = json.decodeFromString<TrailerResponse>(result.bodyAsText())
        return trailer
    }

    override suspend fun getMovieCreditById(movieId: Int): MovieCreditsResponse {
        val result = client.buildHttpClient { encodedPath = "/3/movie/$movieId/credits" }
        val credits = json.decodeFromString<MovieCreditsResponse>(result.bodyAsText())
        return credits
    }

    override suspend fun getMovieReviewsById(movieId: Int): MovieReviewResponse {
        val result = client.buildHttpClient { encodedPath = "/3/movie/$movieId/reviews" }
        val review = json.decodeFromString<MovieReviewResponse>(result.bodyAsText())
        return review
    }

    override suspend fun getSimilarMoviesById(movieId: Int): SimilarMoviesResponse {
        val result = client.buildHttpClient { encodedPath = "/3/movie/$movieId/similar" }
        val similarMovies = json.decodeFromString<SimilarMoviesResponse>(result.bodyAsText())
        return similarMovies
    }

    override suspend fun getSeriesDetailsById(seriesId: Int): SeriesDetailsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getArtistById(artistId: Int): ArtistDetailsResponse {
        TODO("Not yet implemented")
    }

}