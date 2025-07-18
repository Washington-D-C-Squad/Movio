package com.madrid.presentation.screens.searchScreen.viewModel

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@Immutable
data class SearchScreenState(
    val searchUiState: SearchUiState = SearchUiState(),
    val recentSearchUiState: List<String> = emptyList(),
    val filteredScreenUiState: FilteredScreenUiState = FilteredScreenUiState()
) {
    data class SearchUiState(
        val forYouMovies: List<MovieUiState> = emptyList(),
        val exploreMoreMovies: List<MovieUiState> = emptyList(),
        val searchResults: Flow<PagingData<MovieUiState>> = flow {},
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    data class RecentSearchUiState(
        val recentSearch: List<String> = emptyList()
    )

    data class FilteredScreenUiState(
        val searchResultCount: String = "1",
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val topResult: Flow<PagingData<MovieUiState>> = flow {},
        val movie: Flow<PagingData<MovieUiState>> = flow {},
        val series: Flow<PagingData<SeriesUiState>> = flow {},
        val artist: Flow<PagingData<ArtistUiState>> = flow {},
    )


    data class MovieUiState(
        val id: String = "",
        val title: String = "",
        val imageUrl: String = "",
        val rating: String = "",
        val category: String = ""
    )

    data class SeriesUiState(
        val id: String = "",
        val title: String = "",
        val imageUrl: String = "",
        val rating: String = "",
        val category: String = ""
    )

    data class ArtistUiState(
        val id: String = "",
        val name: String = "",
        val role: String = "",
        val country: String? = null,
        val description: String? = null,
        val imageUrl: String = "",
    )

}