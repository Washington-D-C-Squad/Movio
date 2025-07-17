package com.madrid.presentation.screens.searchScreen.viewModel.interactionListener

import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel


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