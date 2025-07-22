package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Series
import com.madrid.domain.usecase.searchUseCase.SeriesUseCase

class SearchSeriesPagingSource(
    private val query: String,
    private val seriesUseCase: SeriesUseCase,
) : BasePagingSource<Series>() {

    override suspend fun loadPage(page: Int): List<Series> {
        return seriesUseCase.invoke(query = query, page = page)
    }
}