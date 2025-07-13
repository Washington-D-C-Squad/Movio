package com.madrid.domain.searchUseCase

import com.madrid.domain.entity.Artist
import com.madrid.domain.interfaces.SearchRepository

class SearchArtistUseCase(
    private val repository: SearchRepository
) {
    suspend fun getAllArtist(): List<Artist> {
        return repository.getAllArtist()
    }

    suspend fun getTopResults(query: String): List<Artist> {
        return repository.getSearchedArtists(query)
    }
}