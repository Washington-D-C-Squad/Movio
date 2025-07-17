package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import kotlinx.coroutines.flow.first

class SearchMoviePagingSource(
    private val query: String,
    private val mediaUseCase: MediaUseCase,
) : BasePagingSource<Movie>() {
    override suspend fun loadPage(page: Int): List<Movie> {
        val pair = mediaUseCase.getTopRatedMedia(query, page).first
        return pair.first()
    }
}