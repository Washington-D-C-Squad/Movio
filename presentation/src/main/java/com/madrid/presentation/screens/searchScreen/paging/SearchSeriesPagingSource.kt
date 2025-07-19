package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Series
import com.madrid.domain.usecase.searchUseCase.MediaUseCase

class SearchSeriesPagingSource(
    private val query: String,
    private val mediaUseCase: MediaUseCase
) : BasePagingSource<Series>() {

    override suspend fun loadPage(page: Int): List<Series> {
        return mediaUseCase.getTopRatedMedia(query = query,page = page).second
    }
}