package com.madrid.data.repositories

import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.Trailer
import com.madrid.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsImpl(

    private val movieDetailsRepository: MovieDetailsRepository
) : MovieDetailsRepository {
    override suspend fun getMovieDetailsById(movieId: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieTrailersById(movieId: Int): Trailer {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCreditsById(movieId: Int): List<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieReviewsById(movieId: Int): List<Review> {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarMoviesById(movieId: Int): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun submitMovieRating(rating: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieToFavourites(movieId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getCollectionsList(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewCollection(collection: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieToList(movieId: Int, listName: String): Boolean {
        TODO("Not yet implemented")
    }


}