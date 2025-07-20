package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.searchUseCase.MovieUseCase

class SearchMoviePagingSource(
    private val query: String,
    private val movieUseCase: MovieUseCase,

) : BasePagingSource<Movie>() {
    override suspend fun loadPage(page: Int): List<Movie> {
        val movies = movieUseCase.invoke(query = query, page = page)
        return movies
    }
}