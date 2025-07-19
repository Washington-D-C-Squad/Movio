package com.madrid.domain.usecase

import com.madrid.domain.repository.RecommendedRepository

class GetExploreMoreMovieUseCase(private val recommendedRepository: RecommendedRepository) {
    suspend operator fun invoke(page: Int) = recommendedRepository.getExploreMoreMovies(page)
}