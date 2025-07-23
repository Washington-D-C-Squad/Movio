package com.madrid.presentation.viewModel.detailsViewModel

data class MovieDetailsUiState(
    val cast: List<CastUiState> = emptyList(),
    val selectedActor: CastUiState? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    data class CastUiState(
        val actorImageUrl: String = "",
        val actorName: String = "",
        val actorRole: String = "",
        val dateOfBirth: String = "",
        val location: String = "location",
        val id: String = "",
        val description: String = "",
        val knownFor: List<KnownMovieUiState> = emptyList(),
        val errorMessage: String? = null
    )

    data class KnownMovieUiState(
        val title: String,
        val imageUrl: String,
        val rating: String
    )
}