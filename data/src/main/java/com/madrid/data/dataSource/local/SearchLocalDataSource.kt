package com.madrid.data.dataSource.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieCategoryEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.SearchLocalSource

class SearchLocalDataSource(
    private val database: MovioDatabase
) : SearchLocalSource {

    // movies
    suspend fun insertMovie(movie: MovieEntity) {
        database.movieDao().insertMovie(movie)
    }

    fun getTopRatedMovies(): List<MovieEntity> {
        return database.movieDao().getTopRatedMovies()
    }

    override suspend fun getMoviesByTitle(query: String): List<MovieEntity> {
        return database.movieDao().getMovieByTitle("%$query%")
    }

    // series
    suspend fun insertSeries(series: SeriesEntity) {
        database.seriesDao().insertSeries(series)
    }

    override suspend fun getSeriesByTitle(query: String): List<SeriesEntity> {
        return database.seriesDao().getSeriesByTitle("%$query%")
    }

    override suspend fun getArtistsByTitle(query: String): List<ArtistEntity> {
        return database.artistDao().getArtistByName("%$query%")
    }

    fun getTopRatedSeries(): List<SeriesEntity> {
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

    fun getRecentCategories(): List<MovieCategoryEntity> {
        return database.movieCategoryDao().getAllCategoriesBySearchCount()
    }

    // recent searches
    override suspend fun getRecentSearches(): List<RecentSearchEntity> {
        return database.recentSearchDao().getRecentSearches()
    }

    override suspend fun addRecentSearch(item: RecentSearchEntity) {
        database.recentSearchDao().addRecentSearch(item)
    }

    override suspend fun removeRecentSearch(query: String) {
        database.recentSearchDao().removeRecentSearch(query)
    }

    override suspend fun clearAllRecentSearches() {
        database.recentSearchDao().clearAllRecentSearches()
    }
}