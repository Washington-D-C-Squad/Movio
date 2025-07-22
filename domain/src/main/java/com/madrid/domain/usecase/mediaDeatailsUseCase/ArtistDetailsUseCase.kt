package com.madrid.domain.usecase.mediaDeatailsUseCase

import com.madrid.domain.entity.Artist
import com.madrid.domain.repository.ArtistDetailsRepository

class ArtistDetailsUseCase(private val artistDetailsRepository: ArtistDetailsRepository) {
    suspend fun getArtistDetailsById(artistId: Int): Artist =
        artistDetailsRepository.getArtistDetailsById(artistId)
}