package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.GetExploreMoreMovieUseCase

class ExplorePagingSource(
    private val getExploreMoreMovieUseCase: GetExploreMoreMovieUseCase
) : BasePagingSource<Movie>() {

    override suspend fun loadPage(page: Int): List<Movie> {
        return getExploreMoreMovieUseCase(page)
    }
}