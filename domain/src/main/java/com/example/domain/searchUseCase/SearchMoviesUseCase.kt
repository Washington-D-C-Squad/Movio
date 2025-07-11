package com.example.domain.searchUseCase

import com.example.domain.entity.Movie
import com.example.domain.interfaces.SearchRepository

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

    suspend fun getSearchedMovie(query: String): List<Movie> {
        return repository.getSearchedMovie(query)
    }
}