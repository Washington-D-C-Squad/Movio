package com.madrid.presentation.screens.searchScreen

import com.madrid.domain.entity.Movie


data class SearchUiState(
    val forYouMovies: List<Movie> = emptyList(),
    val exploreMoreMovies: List<Movie> = emptyList(),
    val searchResults: List<Movie> = emptyList(),
    val recentSearches: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
