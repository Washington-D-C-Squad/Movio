package com.madrid.data.dataSource.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.SearchLocalSource
import kotlinx.coroutines.flow.Flow

class SearchLocalDataSource(
    private val database: MovioDatabase
) : SearchLocalSource {

    // movies
    suspend fun insertMovie(movie: MovieEntity) {
        database.movieDao().insertMovie(movie)
    }

    fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return database.movieDao().getTopRatedMovies()
    }

    override fun getMoviesByTitle(query: String): List<MovieEntity> {
        return database.movieDao().getMovieByTitle("%$query%")
    }

    // series
    suspend fun insertSeries(series: SeriesEntity) {
        database.seriesDao().insertSeries(series)
    }

    override fun getSeriesByTitle(query: String): List<SeriesEntity> {
        return database.artistDao().getArtistByName("%$query%")
    }

    override fun getArtistsByTitle(query: String): List<ArtistEntity> {
        TODO("Not yet implemented")
    }

    fun getTopRatedSeries(): Flow<List<SeriesEntity>> {
        return database.seriesDao().getTopRatedSeries()
    }

    // artists
    suspend fun insertArtist(artist: ArtistEntity) {
        database.artistDao().insertArtist(artist)
    }


    // categories
    suspend fun insertCategory(category: MovieCategoryEntity) {
        database.movieCategoryDao().insertCategory(category)
    }

    fun getRecentCategories(): Flow<List<MovieCategoryEntity>> {
        return database.movieCategoryDao().getAllCategoriesBySearchCount()
    }

    // recent searches
    fun getRecentSearches(): Flow<List<RecentSearchEntity>> {
        return database.recentSearchDao().getRecentSearches()
    }

    suspend fun addRecentSearch(item: RecentSearchEntity) {
        database.recentSearchDao().addRecentSearch(item)
    }

    suspend fun removeRecentSearch(query: String) {
        database.recentSearchDao().removeRecentSearch(query)
    }

    suspend fun clearAllRecentSearches() {
        database.recentSearchDao().clearAllRecentSearches()
    }
}