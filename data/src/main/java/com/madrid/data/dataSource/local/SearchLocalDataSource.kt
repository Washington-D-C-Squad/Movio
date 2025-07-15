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
    override suspend fun insertMovie(movie: MovieEntity) {
        database.movieDao().insertMovie(movie)
    }

    override fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return database.movieDao().getTopRatedMovies()
    }

    override fun getMoviesByTitle(query: String): Flow<List<MovieEntity>> {
        return database.movieDao().getMovieByTitle("%$query%")
    }

    // series
    override suspend fun insertSeries(series: SeriesEntity) {
        database.seriesDao().insertSeries(series)
    }

    override fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>> {
        return database.seriesDao().getSeriesByTitle("%$query%")
    }

    override fun getTopRatedSeries(): Flow<List<SeriesEntity>> {
        return database.seriesDao().getTopRatedSeries()
    }

    // artists
    override suspend fun insertArtist(artist: ArtistEntity) {
        database.artistDao().insertArtist(artist)
    }

    override fun getArtistsByName(query: String): Flow<List<ArtistEntity>> {
        return database.artistDao().getArtistByName("%$query%")
    }

    // categories
    override suspend fun insertCategory(category: MovieCategoryEntity) {
        database.movieCategoryDao().insertCategory(category)
    }

    override fun getRecentCategories(): Flow<List<MovieCategoryEntity>> {
        return database.movieCategoryDao().getAllCategoriesBySearchCount()
    }

    // recent searches
    override fun getRecentSearches(): Flow<List<RecentSearchEntity>> {
        return database.recentSearchDao().getRecentSearches()
    }

    override suspend fun addRecentSearch(item: String) {
        database.recentSearchDao().addRecentSearch(item)
    }

    override suspend fun removeRecentSearch(item: String) {
        database.recentSearchDao().removeRecentSearch(item)
    }

    override suspend fun clearAllRecentSearches() {
        database.recentSearchDao().clearAllRecentSearches()
    }
}