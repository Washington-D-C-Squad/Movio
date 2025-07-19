package com.madrid.presentation.screens.SeeAllForYou

import androidx.paging.PagingData
import com.madrid.presentation.screens.searchScreen.viewModel.SearchScreenState.MovieUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class SeeAllForYouUIState(
    val isLoading : Boolean = false,
    val forYouMovies: Flow<PagingData<MovieUiState>> = flow {}

)
