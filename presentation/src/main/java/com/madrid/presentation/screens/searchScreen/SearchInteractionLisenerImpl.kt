package com.madrid.presentation.screens.searchScreen


val interactionListener = object : SearchInteractionListener {
    override fun onSearchQuerySubmitted(query: String, viewModel: SearchViewModel
    ) {
        if (query.isNotBlank()) {
            viewModel.searchFilteredMovies(query)
            viewModel.searchSeries(query)
            viewModel.artists(query)
            viewModel.topResult(query)
        }
    }
}