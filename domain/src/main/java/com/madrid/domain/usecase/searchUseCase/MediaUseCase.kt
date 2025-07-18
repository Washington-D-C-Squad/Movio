package com.madrid.domain.usecase.searchUseCase

import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SearchRepository

class MediaUseCase(private val searchRepository: SearchRepository) {
    suspend fun getTopRatedMedia( page: Int): Pair<List<Movie>, List<Series>> {
        val movies = searchRepository.getTopRatedMovies( page)
        val series = searchRepository.getTopRatedSeries( page)
        return Pair(movies,series)
    }
    suspend fun getMovieByQuery(
        query: String , page: Int
    ) = searchRepository.getMovieByQuery(query = query , page = page)

    suspend fun getSeriesByQuery(
        query: String, page: Int
    ) = searchRepository.getSeriesByQuery(query = query , page  =page)
}
