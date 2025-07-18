package com.madrid.presentation.screens.searchScreen.viewModel

data class MovieDetailsUiState(
    val cast: List<CastUiState> = listOf(CastUiState()),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    data class CastUiState(
        val name: String = "",
        val imageUrl: String = ""
    )
}