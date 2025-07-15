package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>



    fun getRecentSearches(): Flow<List<MovieCategoryEntity>>

    fun getTopRatedMovies(): Flow<List<MovieEntity>>

    fun getTopRatedSeries(): Flow<List<SeriesEntity>>

    fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>>

    fun getArtistsByTitle(query: String): Flow<List<ArtistEntity>>


}