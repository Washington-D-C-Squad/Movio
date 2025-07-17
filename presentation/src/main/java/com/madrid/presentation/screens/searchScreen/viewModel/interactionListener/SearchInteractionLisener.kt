package com.madrid.presentation.screens.searchScreen.viewModel.interactionListener

import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel

interface SearchInteractionListener {
    fun onSearchQuerySubmitted(query: String,viewModel: SearchViewModel)
}
