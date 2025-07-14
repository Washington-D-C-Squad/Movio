package com.example.data.repositories

import com.example.data.dataSource.local.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>

    fun getSeriesByTitle(query: String): Flow<List<MovieEntity>>

    fun getArtistsByTitle(query: String): Flow<List<MovieEntity>>


}