package com.madrid.domain.repository

import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series

interface HomeRepository {
    suspend fun getMoviesByGenres(): Map<String, List<Movie>>
    suspend fun getSeriesByGenres(): Map<String, List<Series>>
    suspend fun getTopRatedMovies(page: Int): List<Movie>
}