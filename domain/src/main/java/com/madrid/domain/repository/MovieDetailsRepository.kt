package com.madrid.domain.repository

import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Review

interface MovieDetailsRepository {
    suspend fun getMovieById(movieId: Int): Movie
    suspend fun submitMovieRating(rating: Float)
    suspend fun addMovieToFavourites(movieId: Int)
    suspend fun getCollectionsList(): List<String>
    suspend fun addNewCollection(collection: String)
    suspend fun addMovieToList(movieId: Int,listName: String): Boolean
    suspend fun getTopCast(movieId: Int): List<Artist>
    suspend fun getReviews(movieId: Int): List<Review>
    suspend fun getSimilarMovies(genre: List<String>): List<Movie>
}