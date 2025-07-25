package com.madrid.data.repositories

import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.repository.GenreRepository

class GenreRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : GenreRepository {

    override suspend fun getMovieGenres(): List<String> {
        return remoteDataSource.getMovieGenres().genres
            ?.mapNotNull { it.name } ?: emptyList()
    }

    override suspend fun getSeriesGenres(): List<String> {
        return remoteDataSource.getSeriesGenres().genres
            ?.mapNotNull { it.name } ?: emptyList()
    }
}
