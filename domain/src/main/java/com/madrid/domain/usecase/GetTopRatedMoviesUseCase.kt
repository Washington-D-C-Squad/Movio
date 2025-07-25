package com.madrid.domain.usecase

import com.madrid.domain.entity.SearchResult
import com.madrid.domain.repository.MovieRepository

class GetTopRatedMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): SearchResult {
        return repository.getTopRatedMovies(page)
    }
}