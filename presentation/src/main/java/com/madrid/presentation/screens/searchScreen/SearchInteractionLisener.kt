package com.madrid.presentation.screens.searchScreen

interface SearchInteractionListener {
    fun onSearchQuerySubmitted(query: String,viewModel:SearchViewModel)
}
