package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Movie
import com.madrid.domain.repository.RecommendedRepository

class RecommendedRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localSource: LocalDataSource
) : RecommendedRepository{
    override suspend fun getRecommendedMovies(page: Int): List<Movie> {
        val res = remoteDataSource.getPopularMovie(
            page
        ).movieResults?.map {
            it.toMovie()
        } ?: listOf()

        return res
    }

    override suspend fun getExploreMoreMovies(page: Int): List<Movie> {
        val res = remoteDataSource.getTopRatedMovies(
            query = "",
            page = page,
        ).movieResults?.map {
            it.toMovie()
        } ?: listOf()

        return res
    }
}