package com.example.domain.usecase.searchUseCase

import com.example.domain.entity.Movie
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val repository: SearchRepository
) {
    suspend fun getAllMovie(): List<Movie> {
        return repository.getAllMovie()
    }

    suspend fun getTopRatedMovie(): List<Movie> {
        return repository.getTopRatedMovie()
    }

    suspend fun getTopResults(query: String): List<Movie> {
        return repository.getTopResults(query)
    }

    suspend fun getSearchedMovie(query: String): Flow<List<Movie>> {
        return repository.getSearchedMovie(query)
    }
}