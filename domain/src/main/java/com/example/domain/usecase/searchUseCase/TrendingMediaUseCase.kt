package com.example.domain.usecase.searchUseCase

import com.example.domain.repository.SearchRepository

class TrendingMediaUseCase(val searchRepository: SearchRepository) {
    suspend fun getTrendingMedia() = searchRepository.getTrendingMedia()
}