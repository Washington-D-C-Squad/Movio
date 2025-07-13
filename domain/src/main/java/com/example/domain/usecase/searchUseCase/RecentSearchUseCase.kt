package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.RecentSearch
import com.example.domain.repository.SearchRepository

class RecentSearchUseCase(private val searchRepository: SearchRepository) {
    suspend fun getRecentSearches() = searchRepository.getRecentSearches()

    suspend fun addRecentSearch(item: String) = searchRepository.addRecentSearch(item)

    suspend fun removeRecentSearch(item: String) = searchRepository.removeRecentSearch(item)

    suspend fun clearAllRecentSearches() = searchRepository.clearAllRecentSearches()
}