package com.madrid.domain.repository

import com.madrid.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieById(movieId: Int): Movie
    suspend fun submitMovieRating(rating: Float)
    suspend fun addMovieToFavourites(movieId: Int)
    suspend fun getCollectionsList(): Flow<List<String>>
    suspend fun addNewCollection(collection: String)
    suspend fun addMovieToList(movieId: Int,listName: String): Boolean
}