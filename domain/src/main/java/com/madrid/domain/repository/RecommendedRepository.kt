package com.madrid.domain.repository

import com.madrid.domain.entity.Movie

interface RecommendedRepository {
    suspend fun getRecommendedMovies(page: Int): List<Movie> // for you movies
    suspend fun getExploreMoreMovies(page: Int): List<Movie>
}