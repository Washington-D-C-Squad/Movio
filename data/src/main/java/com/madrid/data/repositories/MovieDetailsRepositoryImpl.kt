package com.madrid.data.repositories

import com.madrid.data.dataSource.local.mappers.toMovie
import com.madrid.data.dataSource.local.mappers.toMovieEntity
import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.repositories.datasource.RemoteDataSource
import com.madrid.data.repositories.datasource.LocalDataSource
import com.madrid.domain.entity.Movie
import com.madrid.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localSource: LocalDataSource,
): MovieDetailsRepository {

    override suspend fun getMovieById(movieId: Int): Movie {
        val result = localSource.getMovieById(movieId)
        if (result == null) {
            val movie = remoteDataSource.getMovieById(
                name = query,
            ).movieResults?.map {
                it.toMovie()
            }

            movie?.map {
                localSource.insertMovie(it.toMovieEntity())
            }

        }

        return localSource.searchMovieByQueryFromDB(query).map { it.toMovie() }

    }

    override suspend fun submitMovieRating(rating: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieToFavourites(movieId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getCollectionsList(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewCollection(collection: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieToList(
        movieId: Int,
        listName: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}