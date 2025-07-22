package com.madrid.presentation.viewModel.interactionListener

import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel

interface SearchInteractionListener {
    fun onSearchQuerySubmitted(query: String, viewModel: SearchViewModel, type: String)
}
