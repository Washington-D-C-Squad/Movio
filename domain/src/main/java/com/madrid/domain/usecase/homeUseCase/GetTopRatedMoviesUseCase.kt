package com.madrid.domain.usecase.homeUseCase

import com.madrid.domain.entity.Movie
import com.madrid.domain.repository.HomeRepository

class GetTopRatedMoviesUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.getTopRatedMovies(page)
    }
}
