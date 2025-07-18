package com.madrid.data.dataSource.remote.mapper

import com.madrid.data.dataSource.remote.response.movie.MovieResult
import com.madrid.data.dataSource.remote.response.movie.MovieReviewResponse
import com.madrid.data.dataSource.remote.response.movie.MovieReviewResult
import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.ReviewResult
import com.madrid.domain.entity.SearchResult


fun MovieResult.toMovie(): Movie {
    return Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        imageUrl = "https://image.tmdb.org/t/p/original${this.posterPath}",
        rate = this.voteAverage ?: 0.0,
        yearOfRelease = this.releaseDate ?: "",
        movieDuration = "",
        description = this.overview ?: "",
        genre = listOf(),
    )
}


fun SearchMovieResponse.toSearchResult(): SearchResult {
    return SearchResult(
        page = this.page,
        artistResults = this.movieResults?.map { it.toMovie() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}


/*
fun Cast.tocrew(): Crew {
    return Crew(
        id = this.id?:0,
        name = this.name?:"",
        imageUrl = "https://image.tmdb.org/t/p/original${this.profilePath}",
    )
}*/

fun MovieReviewResponse.toreviewResult(
): ReviewResult {
    return ReviewResult(
        mediaId = this.id ?: 0,
        page = this.page ?: 0,
        results = this.results?.map { it.toReview() } ?: emptyList(),
        totalPages = this.totalPages ?: 0,
        totalResults = this.totalResults ?: 0
    )
}

fun MovieReviewResult.toReview(): Review {
    return Review(
        userId = this.id?.toInt() ?: 0,
        rate = this.authorDetails?.rating ?: 0.0,
        dateOfRelease = this.createdAt ?: "",
        comment = this.content ?: ""
    )
}