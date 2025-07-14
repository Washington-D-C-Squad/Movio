package com.example.domain.usecase.mediaDeatailsUseCase

import com.example.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend fun getMovieById(movieId: Int) = movieDetailsRepository.getMovieById(movieId)

    suspend fun submitMovieRating(rating: Float) = movieDetailsRepository.submitMovieRating(rating)

    suspend fun addMovieToFavourites(movieId: Int) = movieDetailsRepository.addMovieToFavourites(movieId)

    suspend fun getCollectionsList(): Flow<List<String>> = movieDetailsRepository.getCollectionsList()

    suspend fun addNewCollection(collection: String) = movieDetailsRepository.addNewCollection(collection)

    suspend fun addMovieToList(movieId: Int,listName: String): Boolean = movieDetailsRepository.addMovieToList(movieId,listName)
}