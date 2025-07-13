package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.Media
import com.example.domain.repository.SearchRepository

class PreferredMediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun getPreferredMedia(): List<Media> {
        val categories = searchRepository.getMostSearchedCategories()
        val medias: MutableList<Media> = mutableListOf()
        categories.forEach { category ->
            medias.add(searchRepository.getMediaByCategory(category))
        }
        return medias
    }
}
