package com.madrid.domain.usecase.mediaDeatailsUseCase

import com.madrid.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend fun getMovieDetailsById(movieId: Int) =
        movieDetailsRepository.getMovieDetailsById(movieId)

    suspend fun getMovieTrailersById(movieId: Int) =
        movieDetailsRepository.getMovieTrailersById(movieId)

    suspend fun getMovieCreditsById(movieId: Int) =
        movieDetailsRepository.getMovieCreditsById(movieId)

    suspend fun getMovieReviewsById(movieId: Int) =
        movieDetailsRepository.getMovieCreditsById(movieId)

    suspend fun getSimilarMoviesById(movieId: Int) =
        movieDetailsRepository.getSimilarMoviesById(movieId)

    suspend fun submitMovieRating(rating: Float) = movieDetailsRepository.submitMovieRating(rating)

    suspend fun addMovieToFavourites(movieId: Int) = movieDetailsRepository.addMovieToFavourites(movieId)

    suspend fun getCollectionsList(): Flow<List<String>> = movieDetailsRepository.getCollectionsList()

    suspend fun addNewCollection(collection: String) = movieDetailsRepository.addNewCollection(collection)

    suspend fun addMovieToList(movieId: Int,listName: String): Boolean = movieDetailsRepository.addMovieToList(movieId,listName)
}