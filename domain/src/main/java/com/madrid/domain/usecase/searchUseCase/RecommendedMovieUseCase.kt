package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class RecommendedMovieUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke () = searchRepository.getRecommendedMovie()
}