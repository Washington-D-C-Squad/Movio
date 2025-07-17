package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class MovieUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(query: String) = searchRepository.getMovieByQuery(query)
}