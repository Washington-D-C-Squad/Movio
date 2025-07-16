package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.domain.entity.Artist
import kotlinx.coroutines.flow.Flow

interface SearchLocalSource {

    // movies
    suspend fun insertMovie(movie: MovieEntity)

    fun getMoviesByTitle(query: String): Flow<List<MovieEntity>>

    fun getTopRatedMovies(): Flow<List<MovieEntity>>


    // series
    suspend fun insertSeries(series: SeriesEntity)

    fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>>

    fun getTopRatedSeries(): Flow<List<SeriesEntity>>

    // artists
    suspend fun insertArtist(artist: ArtistEntity)

    fun getArtistsByName(query: String): Flow<List<ArtistEntity>>

    // categories
    suspend fun insertCategory(category: MovieCategoryEntity)

    fun getRecentCategories(): Flow<List<MovieCategoryEntity>>

    // recent searches
    fun getRecentSearches(): Flow<List<RecentSearchEntity>>

    suspend fun addRecentSearch(item: RecentSearchEntity)

    suspend fun removeRecentSearch(query: String)

    suspend fun clearAllRecentSearches()


}