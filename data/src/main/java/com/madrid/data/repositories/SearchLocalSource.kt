package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    // insert
    suspend fun insertMovie(movie: MovieEntity)




    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>


    // series
    suspend fun insertSeries(series: SeriesEntity)

    fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>>
    // artists
    suspend fun insertArtist(series: ArtistEntity)

    fun getArtistsByName(query: String): Flow<List<ArtistEntity>>


    // recent searches
    fun getRecentSearches(): Flow<List<MovieCategoryEntity>>

    fun getTopRatedMovies(): Flow<List<MovieEntity>>

    fun getTopRatedSeries(): Flow<List<SeriesEntity>>




}