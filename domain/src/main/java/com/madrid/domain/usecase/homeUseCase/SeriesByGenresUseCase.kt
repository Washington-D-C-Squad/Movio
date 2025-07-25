package com.madrid.domain.usecase.homeUseCase

import com.madrid.domain.entity.Series
import com.madrid.domain.repository.HomeRepository

class SeriesByGenresUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Map<String, List<Series>> {
        return homeRepository.getSeriesByGenres()
    }
}