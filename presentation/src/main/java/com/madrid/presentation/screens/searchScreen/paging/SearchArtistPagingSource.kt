package com.madrid.presentation.screens.searchScreen.paging

import com.madrid.domain.entity.Artist
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase

class SearchArtistPagingSource(
    private val query: String,
    private val artistUseCase: ArtistUseCase
) : BasePagingSource<Artist>() {

    override suspend fun loadPage(page: Int): List<Artist> {
        return artistUseCase.getArtistByQuery(query, page)
    }
}
