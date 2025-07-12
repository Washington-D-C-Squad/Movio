package com.example.domain.searchUseCase

import com.example.domain.entity.Artist
import com.example.domain.interfaces.SearchRepository

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