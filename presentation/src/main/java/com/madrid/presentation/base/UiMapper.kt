package com.madrid.presentation.base

import com.madrid.presentation.screens.searchScreen.SearchScreenState
import com.madrid.domain.entity.Movie

fun Movie.toUiState() = SearchScreenState.MovieUiState(
    id = String(),
    title = title.toString(),
    imageUrl = imageUrl.toString(),
    rating = rate.toString(),
    category = String()
)


