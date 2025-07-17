package com.madrid.domain.usecase.mediaDeatailsUseCase

import com.madrid.domain.repository.MovieDetailsRepository

class MovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend fun getMovieById(movieId: Int) = movieDetailsRepository.getMovieById(movieId)
    suspend fun submitMovieRating(rating: Float) = movieDetailsRepository.submitMovieRating(rating)
    suspend fun addMovieToFavourites(movieId: Int) = movieDetailsRepository.addMovieToFavourites(movieId)
    suspend fun getCollectionsList(): List<String> = movieDetailsRepository.getCollectionsList()
    suspend fun addNewCollection(collection: String) = movieDetailsRepository.addNewCollection(collection)
    suspend fun addMovieToList(movieId: Int,listName: String): Boolean = movieDetailsRepository.addMovieToList(movieId,listName)
    suspend fun getTopCast(movieId: Int) = movieDetailsRepository.getTopCast(movieId)
    suspend fun getReviews(movieId: Int) = movieDetailsRepository.getReviews(movieId)
    suspend fun getSimilarMovies(genre: List<String>) = movieDetailsRepository.getSimilarMovies(genre)

}