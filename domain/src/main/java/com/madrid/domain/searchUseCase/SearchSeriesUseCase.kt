package com.madrid.domain.searchUseCase

import com.madrid.domain.entity.Series
import com.madrid.domain.interfaces.SearchRepository

class SearchSeriesUseCase(
    private val repository: SearchRepository
) {
    suspend fun getAllSeries(): List<Series> {
        return repository.getAllSeries()
    }

    suspend fun getTopRatedMovie(): List<Series> {
        return repository.getTopRatedSeries()
    }

    suspend fun getTopResults(query: String): List<Series> {
        return repository.getSearchedSeries(query)
    }
}