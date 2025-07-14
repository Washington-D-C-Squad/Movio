package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.entity.Media
import com.madrid.domain.repository.SearchRepository

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
