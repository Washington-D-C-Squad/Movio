package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class SeriesUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(
        query: String, page: Int = 1
    ) = searchRepository.getSeriesByQuery(query = query, page = page)
}