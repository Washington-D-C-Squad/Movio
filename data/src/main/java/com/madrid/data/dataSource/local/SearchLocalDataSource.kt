package com.madrid.data.dataSource.local

import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.repositories.SearchLocalSource
import com.madrid.data.repositories.mappers.toMovieEntity
import com.madrid.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSource(

) : SearchLocalSource {


    val dao = MovioDatabase.Companion.getInstanceWithoutContext()

    suspend fun insertMovie(movie: Movie) {
        dao.movieDao().insertMovie(
            movie.toMovieEntity()
        )
    }

    override fun getMoviesByTitle(query: String): Flow<List<MovieEntity>> {
        return dao.movieDao().getMovieByTitle("%$query%")
    }

    override fun getSeriesByTitle(query: String): Flow<List<MovieEntity>> {
        return dao.seriesDao().getSeriesByTitle("%$query%")
    }

    override fun getArtistsByTitle(query: String): Flow<List<MovieEntity>> {
        return dao.artistDao().getArtistByName("%$query%")
    }


}