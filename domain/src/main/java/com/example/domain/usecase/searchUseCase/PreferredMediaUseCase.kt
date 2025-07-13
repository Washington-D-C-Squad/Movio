package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.Media
import com.example.domain.repository.SearchRepository

class PreferredMediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun getPreferredMedia(): List<Media> {
        val categories = searchRepository.getMostSearchedCategories()
        var media: List<Media> = emptyList()
        categories.forEach { category ->
            media = media + searchRepository.getMediaByCategory(category)
        }
        return media
    }
}
