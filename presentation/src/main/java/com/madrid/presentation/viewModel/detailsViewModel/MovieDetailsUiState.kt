package com.madrid.presentation.viewModel.detailsViewModel

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
