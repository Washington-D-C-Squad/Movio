package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.Series
import com.example.domain.repository.SearchRepository

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