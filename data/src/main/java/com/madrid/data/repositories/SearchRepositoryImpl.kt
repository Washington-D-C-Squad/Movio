package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.mappers.toArtist
import com.madrid.data.dataSource.local.mappers.toArtistEntity
import com.madrid.data.dataSource.local.mappers.toMovie
import com.madrid.data.dataSource.local.mappers.toMovieEntity
import com.madrid.data.dataSource.local.mappers.toSeries
import com.madrid.data.dataSource.local.mappers.toSeriesEntity
import com.madrid.data.dataSource.remote.mapper.toArtist
import com.madrid.data.dataSource.remote.mapper.toMovie
import com.madrid.data.dataSource.remote.mapper.toSeries
import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Media
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localSource: SearchLocalSource
) : SearchRepository {


    override suspend fun getMovieByQuery(query: String): List<Movie> {
        val result = localSource.getMoviesByTitle(query)
        if (result.isEmpty()) {
            remoteDataSource.searchMoviesByQuery(
                name = query,
            ).movieResults.map {
                it.toMovie()
            }
        }
        return localSource.getMoviesByTitle(query).map { it.toMovie() }
    }

    override suspend fun getSeriesByQuery(query: String): List<Series> {
        val result = localSource.getSeriesByTitle(query)
        if (result.isEmpty()) {
            remoteDataSource.searchSeriesByQuery(
                name = query,
            ).seriesResults.map {
                it.toSeries()
            }
        }
        return localSource.getSeriesByTitle(query).map { it.toSeries() }
    }

    override suspend fun getArtistByQuery(query: String): List<Artist> {
        val result = localSource.getArtistsByTitle(query)
        if (result.isEmpty()) {
            remoteDataSource.searchArtistByQuery(
                name = query,
            ).artistResults.map {
                it.toArtist()
            }
        }
        return localSource.getArtistsByTitle(query).map { it.toArtist() }
    }

    override suspend fun getMediaByQuery(query: String): Flow<List<Media>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMostSearchedCategories(): List<String> {
//        val localCategories = localSource.getRecentCategories()
//        return localCategories.first().map { it.title }
        TODO()
    }


    override suspend fun getMediaByCategory(category: String): Media {
        TODO("Not yet implemented")
    }


    //////////////
    override suspend fun getTrendingMedia(): Media {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(query: String): List<Movie> {
//        val localMoviesEntities = localSource.getTopRatedMovies()
//         return localMoviesEntities.map { it.toMovie() }
        TODO()
    }

    override suspend fun getTopRatedSeries(query: String): List<Series> {
//        val localSeriesEntities = localSource.getTopRatedSeries()
//        return localSeriesEntities.map { it.toSeries() }
        TODO()
    }


    override suspend fun getRecentSearches(): List<String> {
        return localSource.getRecentSearches().map { it.searchQuery }
    }

    override suspend fun addRecentSearch(item: String) {
        val recentSearchEntity = RecentSearchEntity(
            searchQuery = item
        )
        localSource.addRecentSearch(recentSearchEntity)
    }

    override suspend fun removeRecentSearch(item: String) {
        localSource.removeRecentSearch(item)
    }

    override suspend fun clearAllRecentSearches() {
        localSource.clearAllRecentSearches()
    }
}