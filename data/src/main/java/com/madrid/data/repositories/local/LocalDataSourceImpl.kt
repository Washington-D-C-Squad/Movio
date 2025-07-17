package com.madrid.data.repositories.local

import android.content.Context
import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LocalDataSourceImpl(
    private val context: Context
) : LocalDataSource {


    private val dao = MovioDatabase.getInstance(context)

    suspend fun insertMovie(movie: MovieEntity) {
        dao.movieDao().insertMovie(movie)
    }

    suspend fun insertSeries(series: SeriesEntity) {
        dao.seriesDao().insertSeries(series)
    }

    fun getTopRatedMovies(): Flow<List<MovieEntity>> {
        return dao.movieDao().getTopRatedMovies()
    }


    override suspend fun searchMovieByQueryFromDB(query: String): List<MovieEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun searchSeriesByQueryFromDB(query: String): List<SeriesEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun searchArtistByQueryFromDB(query: String): List<ArtistEntity> {
        TODO("Not yet implemented")
    }

    private val recentSearches = MutableStateFlow<List<String>>(emptyList())

    override suspend fun getRecentSearches(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

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