package com.madrid.data.repositories.remote

import com.madrid.data.CustomHttpClient
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
import com.madrid.data.dataSource.remote.utils.Constants.QUERY
import io.ktor.client.statement.bodyAsText
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class RemoteDataSourceImpl(
    private val client: CustomHttpClient,
    private val json: Json,
) : RemoteDataSource {

    override suspend fun searchMoviesByQuery(name: String): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/movie"
            parameters.append(QUERY, name)
        }
        val movies = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())
        return movies
    }

    override suspend fun searchSeriesByQuery(name: String): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/search/tv"
            parameters.append(QUERY, name)
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    override suspend fun searchArtistByQuery(name: String): SearchArtistResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/search/person"
            parameters.append(QUERY, name)
        }
        val artist = json.decodeFromString<SearchArtistResponse>(result.bodyAsText())

        return artist
    }

    override suspend fun getTopRatedMovies(): SearchMovieResponse {

        val result = client.buildHttpClient {
            encodedPath = "/3/movie/top_rated"
        }
        val movie = json.decodeFromString<SearchMovieResponse>(result.bodyAsText())

        return movie
    }

    override suspend fun getTopRatedSeries(): SearchSeriesResponse {
        val result = client.buildHttpClient {
            encodedPath = "/3/tv/top_rated"
        }
        val series = json.decodeFromString<SearchSeriesResponse>(result.bodyAsText())
        return series
    }

    override suspend fun getMovieDetailsById(movieId: Int): MovieDetailsResponse {
        val result = client.buildHttpClient { encodedPath = "/3/movie/$movieId" }
        val movie = json.decodeFromString<MovieDetailsResponse>(result.bodyAsText())
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