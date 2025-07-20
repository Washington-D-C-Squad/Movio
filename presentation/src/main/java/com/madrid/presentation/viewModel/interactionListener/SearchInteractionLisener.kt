package com.madrid.presentation.viewModel.interactionListener

import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel

interface SearchInteractionListener {
    fun onSearchFilteredMovies(query: String,viewModel: SearchViewModel)
    fun onSearchFilmSubmitted(query: String, viewModel: SearchViewModel)
    fun onSearchSeries(query: String, viewModel: SearchViewModel)
    fun onSearchArtists(query: String, viewModel: SearchViewModel)
    fun onSearchTopResult(query: String, viewModel: SearchViewModel)

}
