package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class TrendingMediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun getTrendingMedia() = searchRepository.getPopularMovie()
}