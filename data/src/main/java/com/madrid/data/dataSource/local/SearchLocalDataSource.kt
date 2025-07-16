package com.madrid.data.dataSource.local

import android.content.Context
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.repositories.SearchLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchLocalDataSource(
    private val context: Context
) : SearchLocalSource {


    private val dao = MovioDatabase.Companion.getInstance(context)

    suspend fun insertMovie(movie: MovieEntity) {
        dao.movieDao().insertMovie(movie)
    }

    suspend fun insertSeries(series: SeriesEntity) {
        dao.seriesDao().insertSeries(series)
    }

    fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return dao.movieDao().getTopRatedMovies()
    }

    override fun getMoviesByTitle(query: String): Flow<List<MovieEntity>> {
        return dao.movieDao().getMovieByTitle("%$query%")
    }

    override fun getSeriesByTitle(query: String): Flow<List<SeriesEntity>> {
        return dao.seriesDao().getSeriesByTitle("%$query%")
    }

    override fun getArtistsByTitle(query: String): Flow<List<ArtistEntity>> {
        return dao.artistDao().getArtistByName("%$query%")
    }

    private val recentSearches = MutableStateFlow<List<String>>(emptyList())

    override fun getRecentSearches() = recentSearches.asStateFlow()

    override suspend fun addRecentSearch(item: String) {
        val current = recentSearches.value.toMutableList()
        if (current.contains(item)) current.remove(item)
        current.add(0, item)
        recentSearches.value = current.take(10)
    }

    override suspend fun removeRecentSearch(item: String) {
        val current = recentSearches.value.toMutableList()
        current.remove(item)
        recentSearches.value = current
    }

    override suspend fun clearAllRecentSearches() {
        recentSearches.value = emptyList()
    }

}