package com.madrid.domain.repository

import com.madrid.domain.entity.Artist

interface ArtistDetailsRepository {
    suspend fun getArtistDetailsById(artistId: Int): Artist
}