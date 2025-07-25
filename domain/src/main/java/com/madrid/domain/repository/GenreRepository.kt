package com.madrid.domain.repository

interface GenreRepository {
    suspend fun getMovieGenres(): List<String>
    suspend fun getSeriesGenres(): List<String>
}