package com.madrid.domain.usecase

import com.madrid.domain.repository.RecommendedRepository

class GetRecommendedMovieUseCase(private val recommendedRepository: RecommendedRepository) {
    suspend operator fun invoke(page: Int) = recommendedRepository.getRecommendedMovies(page)
}