package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class PopularMovieUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke() = searchRepository.getPopularMovie()
}