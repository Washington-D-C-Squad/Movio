package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.repository.SearchRepository

class RecentSearchUseCase(private val searchRepository: SearchRepository) {
    suspend fun getRecentSearches() = searchRepository.getRecentSearches()

    suspend fun addRecentSearch(item: String) = searchRepository.addRecentSearchByQuery(item)

    suspend fun removeRecentSearch(item: String) = searchRepository.removeRecentSearchByQuery(item)

    suspend fun clearAllRecentSearches() = searchRepository.clearAllRecentSearches()
}