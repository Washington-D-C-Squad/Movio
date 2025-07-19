package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Movie
import com.madrid.domain.usecase.GetExploreMoreMovieUseCase
import com.madrid.domain.usecase.GetRecommendedMovieUseCase

class ForYouPagingSource(
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase
) : BasePagingSource<Movie>() {

    override suspend fun loadPage(page: Int): List<Movie> {
        return getRecommendedMovieUseCase(page)
    }
}