package com.madrid.presentation.screens.searchScreen

data class SearchScreenState(
    val searchUiState: SearchUiState = SearchUiState(),
    val recentSearchUiState: List<String> = emptyList(),

    //TODO add the remain ui state
) {
    data class SearchUiState(
        val forYouMovies: List<MovieUiState> = listOf(MovieUiState()),
        val exploreMoreMovies: List<MovieUiState> = listOf(MovieUiState()),
        val searchResults: List<MovieUiState> = listOf(MovieUiState()),
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
    //TODO add the remain ui state
}