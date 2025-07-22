package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class MovieUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(
        query: String, page: Int = 1
    ) = searchRepository.getMovieByQuery(query = query , page = page)
}