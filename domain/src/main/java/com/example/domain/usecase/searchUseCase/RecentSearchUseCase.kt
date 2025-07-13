package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.RecentSearch
import com.example.domain.repository.SearchRepository

class RecentSearchUseCase(private val searchRepository: SearchRepository) {
    fun getRecentSearches() = searchRepository.getRecentSearches()

    fun addRecentSearch(item: RecentSearch) = searchRepository.addRecentSearch(item)

    fun removeRecentSearch(id: Int) = searchRepository.removeRecentSearch(id)

    fun clearAllRecentSearches() = searchRepository.clearAllRecentSearches()
}