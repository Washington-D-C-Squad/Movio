package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.searchUseCase.MediaUseCase

class SearchMoviePagingSource(
    private val query: String,
    private val mediaUseCase: MediaUseCase,
) : BasePagingSource<Movie>() {
    override suspend fun loadPage(page: Int): List<Movie> {
        val movies = mediaUseCase.getTopRatedMedia(query = query, page = page).first
        return movies
    }
}