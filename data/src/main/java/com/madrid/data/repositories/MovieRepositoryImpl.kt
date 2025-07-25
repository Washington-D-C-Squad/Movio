package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.response.movie.SearchMovieResponse
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Movie
import com.madrid.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: RemoteDataSource
) : MovieRepository {
    override suspend fun getTopRatedMovies(page: Int): SearchMovieResponse {
        return api.getTopRatedMovies(page)
    }
}

