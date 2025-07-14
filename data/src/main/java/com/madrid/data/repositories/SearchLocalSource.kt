package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>

    fun getSeriesByTitle(query: String): Flow<List<MovieEntity>>

    fun getArtistsByTitle(query: String): Flow<List<MovieEntity>>


}