package com.example.domain.usecase.searchUseCase

import com.example.domain.repository.SearchRepository

class MediaUseCase(val searchRepository: SearchRepository) {
    suspend fun getTopRatedMedia(query: String) = searchRepository.getTopRatedMedia(query)
    suspend fun getMovieByQuery(query: String) = searchRepository.getMovieByQuery(query)
    suspend fun getSeriesByQuery(query: String) = searchRepository.getSeriesByQuery(query)
}