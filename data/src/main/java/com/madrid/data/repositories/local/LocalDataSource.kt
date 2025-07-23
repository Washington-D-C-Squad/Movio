package com.madrid.data.repositories.local

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieGenreEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.dataSource.local.entity.SeriesGenreEntity
import com.madrid.data.dataSource.local.entity.relationship.MovieGenreCrossRef

interface LocalDataSource {

    suspend fun insertMovie(movie: MovieEntity)
    suspend fun insertSeries(series: SeriesEntity)
    suspend fun insertArtist(artist: ArtistEntity)
    suspend fun insertMovieGenre(genre: MovieGenreEntity)
    suspend fun insertSeriesGenre(genre: SeriesGenreEntity)

    suspend fun getTopRatedMovies(): List<MovieEntity>

    suspend fun searchMovieByQueryFromDB(query: String, page: Int): List<MovieEntity>
    suspend fun searchSeriesByQueryFromDB(query: String, page: Int): List<SeriesEntity>
    suspend fun searchArtistByQueryFromDB(query: String, page: Int): List<ArtistEntity>

    suspend fun getRecentSearches(): List<RecentSearchEntity>
    suspend fun addRecentSearch(item: String)
    suspend fun removeRecentSearch(item: String)
    suspend fun clearAllRecentSearches()

    suspend fun relateMovieToCategory(movieCategoryEntity: MovieGenreCrossRef)
    suspend fun addSearchedCategoryCount(categoryTitle: String)

    suspend fun getAllMovieGenres(): List<MovieGenreEntity>
}