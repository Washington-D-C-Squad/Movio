package com.madrid.presentation.screens.searchScreen

data class SearchScreenState(
    val searchUiState: SearchUiState = SearchUiState(),
    val recentSearchUiState: List<String> = emptyList(),
) {
    data class SearchUiState(
        val forYouMovies: List<MovieUiState> = emptyList(),
        val exploreMoreMovies: List<MovieUiState> = emptyList(),
        val searchResults: List<MovieUiState> =emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
    data class MovieUiState(
        val id: String="",
        val title: String="",
        val imageUrl: String="",
        val rating: String="",
        val category: String=""
    )
    data class SeriesUiState(
        val id: String,
        val title: String,
        val imageUrl: String,
        val rating: String,
        val category: String
    )
    data class RecentSearchUiState (
        val recentSearch : List<String> = emptyList()
    )
}