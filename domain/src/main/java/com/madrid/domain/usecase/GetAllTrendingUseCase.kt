package com.madrid.domain.usecase

import com.madrid.domain.repository.AllTrendingRepository

class GetAllTrendingUseCase(
    private val getAllTrendingRepository: AllTrendingRepository
) {
    suspend operator fun invoke(page: Int) = getAllTrendingRepository.getAllTrending(page)
}