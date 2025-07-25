package com.madrid.domain.usecase.generUseCase

import com.madrid.domain.repository.GenreRepository

class GetSeriesGenresUseCase(
    private val repository: GenreRepository
) {
    suspend operator fun invoke(): List<String> {
        return repository.getSeriesGenres()
    }
}