package com.example.presentation.component.screens.SearchScreen



data class SearchUiState(
    val forYouMovies: List<Movie> = emptyList(),
    val exploreMoreMovies: List<Movie> = emptyList(),
    val searchResults: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
