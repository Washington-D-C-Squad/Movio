package com.example.data.dataSource.local

import com.example.data.dataSource.local.data.MovioDatabase
import com.example.data.dataSource.local.data.entity.MovieEntity
import com.example.data.repositories.SearchLocalSource
import com.example.data.repositories.mappers.toMovieEntity
import com.example.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSource(

) : SearchLocalSource {


    val dao = MovioDatabase.getInstanceWithoutContext()

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