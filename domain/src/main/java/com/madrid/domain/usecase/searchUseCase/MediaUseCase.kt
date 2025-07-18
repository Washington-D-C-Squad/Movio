package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository

class MediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun getTopRatedMedia(query: String, page: Int): Pair<List<Movie>, List<Series>> {
        val movies = searchRepository.getTopRatedMovies(query, page)
        val series = searchRepository.getTopRatedSeries(query , page)
        return Pair(movies,series)
    }
    suspend fun getMovieByQuery(query: String) = searchRepository.getMovieByQuery(query)
    suspend fun getSeriesByQuery(query: String) = searchRepository.getSeriesByQuery(query)
}
