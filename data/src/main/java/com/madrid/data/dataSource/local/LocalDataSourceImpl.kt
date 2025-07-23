package com.madrid.data.dataSource.local

import com.madrid.data.dataSource.local.dao.ArtistDao
import com.madrid.data.dataSource.local.dao.MovieGenreDao
import com.madrid.data.dataSource.local.dao.MovieDao
import com.madrid.data.dataSource.local.dao.RecentSearchDao
import com.madrid.data.dataSource.local.dao.SeriesDao
import com.madrid.data.dataSource.local.dao.SeriesGenreDao
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieGenreEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.dataSource.local.entity.SeriesGenreEntity
import com.madrid.data.dataSource.local.entity.relationship.MovieGenreCrossRef
import com.madrid.data.repositories.local.LocalDataSource

class LocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
    private val artistDao: ArtistDao,
    private val movieGenreDao: MovieGenreDao,
    private val seriesGenreDao: SeriesGenreDao,
    private val recentSearchDao: RecentSearchDao
) : LocalDataSource {


    override suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    override suspend fun insertSeries(series: SeriesEntity) {
        seriesDao.insertSeries(series)
    }

    override suspend fun getTopRatedMovies(): List<MovieEntity> {
        return movieDao.getTopRatedMovies()
    }

    override suspend fun insertArtist(artist: ArtistEntity) {
        artistDao.insertArtist(artist = artist)
    }

    override suspend fun insertMovieGenre(genre: MovieGenreEntity) {
        movieGenreDao.insertGenre(genre)
    }

    override suspend fun insertSeriesGenre(genre: SeriesGenreEntity) {
        seriesGenreDao.insertGenre(genre)
    }

    override suspend fun searchMovieByQueryFromDB(query: String, page: Int): List<MovieEntity> {
        val offset = (page - 1) * 20
        return movieDao.getAllMoviesSortedByCategorySearchCount("%$query%", offset).map { it.movie }
    }

    override suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity> {
        return seriesDao.getSeriesByTitle("%$query%")
    }

    override suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity> {
        return artistDao.getArtistByName("%$query%")
    }


    override suspend fun getRecentSearches(): List<RecentSearchEntity> {
        return recentSearchDao.getRecentSearches()
    }

    override suspend fun addRecentSearch(item: String) {
        recentSearchDao.addRecentSearch(
            RecentSearchEntity(
                searchQuery = item,
            )
        )
    }

    override suspend fun removeRecentSearch(item: String) {
        recentSearchDao.removeRecentSearch(
            item
        )
    }

    override suspend fun clearAllRecentSearches() {
        recentSearchDao.clearAllRecentSearches()
    }

    override suspend fun relateMovieToCategory(movieCategoryEntity: MovieGenreCrossRef) {
        movieDao.insertMovieCategoryCrossRef(movieCategoryEntity)
    }

    override suspend fun addSearchedCategoryCount(categoryTitle: String) {
        movieGenreDao.increaseCategorySearchCount(categoryTitle)
    }

    override suspend fun getAllMovieGenres(): List<MovieGenreEntity> {
        return movieGenreDao.getAllCategories()
    }
}