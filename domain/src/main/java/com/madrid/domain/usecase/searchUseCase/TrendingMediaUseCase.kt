package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class TrendingMediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun invoke(
        page: Int = 1
    ) = searchRepository.getPopularMovie(page)
}