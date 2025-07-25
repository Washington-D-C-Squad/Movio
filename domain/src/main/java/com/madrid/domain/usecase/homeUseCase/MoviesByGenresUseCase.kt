package com.madrid.domain.usecase.homeUseCase

import com.madrid.domain.entity.Movie
import com.madrid.domain.repository.HomeRepository

class MoviesByGenresUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(genre: String): Map<String, List<Movie>> {
        return homeRepository.getMoviesByGenres()
    }
}