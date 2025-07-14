package com.example.data.repositories

import com.example.data.dataSource.local.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    fun getMoviesByName(query: String): Flow<List<MovieEntity>>

    fun getSeriesByName(query: String): Flow<List<MovieEntity>>

    fun getArtistsByName(query: String): Flow<List<MovieEntity>>


}