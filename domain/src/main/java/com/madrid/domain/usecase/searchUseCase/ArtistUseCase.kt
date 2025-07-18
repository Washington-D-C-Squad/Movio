package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.entity.Artist
import com.madrid.domain.repository.SearchRepository

class ArtistUseCase(private val searchRepository: SearchRepository) {
    suspend fun getArtistByQuery(query: String) = searchRepository.getArtistByQuery(query)
}