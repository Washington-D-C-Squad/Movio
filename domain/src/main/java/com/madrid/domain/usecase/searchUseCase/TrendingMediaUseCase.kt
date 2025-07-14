package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class TrendingMediaUseCase(val searchRepository: SearchRepository) {
    suspend fun getTrendingMedia() = searchRepository.getTrendingMedia()
}