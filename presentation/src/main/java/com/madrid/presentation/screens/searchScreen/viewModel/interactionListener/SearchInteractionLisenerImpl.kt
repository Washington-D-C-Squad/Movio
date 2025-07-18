package com.madrid.presentation.screens.searchScreen.viewModel.interactionListener

import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel


val interactionListener = object : SearchInteractionListener {

    override fun onSearchFilteredMovies(query: String, viewModel: SearchViewModel) {
        if (query.isNotBlank()) {
            viewModel.searchFilteredMovies(query)
        }
    }

    override fun onSearchFilmSubmitted(query: String, viewModel: SearchViewModel) {
        if (query.isNotBlank()) {
            viewModel.searchMovies(query)
            viewModel.addRecentSearch(query)
        }
    }

    override fun onSearchSeries(query: String, viewModel: SearchViewModel) {
        if (query.isNotBlank()) {
            viewModel.searchSeries(query)
        }
    }

    override fun onSearchArtists(query: String, viewModel: SearchViewModel) {
        if (query.isNotBlank()) {
            viewModel.artists(query)
        }
    }

    override fun onSearchTopResult(query: String, viewModel: SearchViewModel) {
        if (query.isNotBlank()) {
            viewModel.artists(query)
        }
    }
}