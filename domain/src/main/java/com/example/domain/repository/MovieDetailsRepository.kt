package com.example.domain.repository

import com.example.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieById(movieId: Int): Movie
    suspend fun submitMovieRating(rating: Float)
    suspend fun addMovieToFavourites(id: Int)
    suspend fun getCollectionsList(): Flow<List<String>>
    suspend fun addNewCollection(collection: String)
    suspend fun addMovieToList(movieId: Int,list: String): Boolean
}