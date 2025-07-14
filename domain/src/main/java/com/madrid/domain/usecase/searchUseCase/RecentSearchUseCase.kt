package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class RecentSearchUseCase(private val searchRepository: SearchRepository) {
    suspend fun getRecentSearches() = searchRepository.getRecentSearches()

    suspend fun addRecentSearch(item: String) = searchRepository.addRecentSearch(item)

    suspend fun removeRecentSearch(item: String) = searchRepository.removeRecentSearch(item)

    suspend fun clearAllRecentSearches() = searchRepository.clearAllRecentSearches()
}