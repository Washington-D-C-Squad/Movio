package com.madrid.domain.repository

import com.madrid.domain.entity.SearchResult


interface MovieRepository {
    suspend fun getTopRatedMovies(page: Int): SearchResult
}
