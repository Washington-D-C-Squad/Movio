package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class ArtistUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(query: String) = searchRepository.getArtistByQuery(query)
}