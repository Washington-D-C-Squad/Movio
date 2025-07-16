package com.madrid.presentation.screens.searchScreen


data class SearchScreenState(
    val searchUiState: SearchUiState = SearchUiState(),
    val recentSearchUiState: List<String> = emptyList(),
    val filteredScreenUiState: FilteredScreenUiState = FilteredScreenUiState()
) {
    data class SearchUiState(
        val forYouMovies: List<MovieUiState> =  emptyList(),
        val exploreMoreMovies: List<MovieUiState> =  emptyList(),
        val searchResults: List<MovieUiState> =  emptyList(),
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
        val topResult: List<MovieUiState> = emptyList(),
        val movie: List<MovieUiState> = emptyList(),
        val series: List<SeriesUiState> = emptyList(),
        val artist: List<ArtistUiState> = emptyList(),

    )


    data class MovieUiState(
        val id: String = "",
        val title: String = "",
        val imageUrl: String = "",
        val rating: String = "",
        val category: String = ""
    )
    data class SeriesUiState(
        val id: String ="",
        val title: String="",
        val imageUrl: String="",
        val rating: String="",
        val category: String=""
    )
    data class ArtistUiState(
        val id: String ="",
        val name:String ="",
        val role: String = "",
        val country: String?=null,
        val description: String?=null,
        val imageUrl : String ="",
    )

}