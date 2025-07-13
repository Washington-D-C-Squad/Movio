package com.example.domain.usecase.searchUseCase

import com.example.domain.repository.SearchRepository

class ArtistUseCase(private val searchRepository: SearchRepository) {
    suspend fun getArtistByQuery(query: String) = searchRepository.getArtistByQuery(query)
}