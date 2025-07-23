package com.madrid.movio.di


import com.madrid.presentation.screens.searchScreen.SeeAllForYou.SeeAllForYouViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import com.madrid.presentation.viewModel.detailsViewModel.TopCastViewModel
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsViewModel
import com.madrid.presentation.viewModel.detailsViewModel.SeriesDetailsViewModel
import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.madrid.presentation.viewModel.detailsViewModel.ActorDetailsViewModel
import org.koin.dsl.module


val presentationModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailsMovieViewModel)
    viewModelOf(::SeeAllForYouViewModel)
    viewModelOf(::TopCastViewModel)
    viewModelOf(::ActorDetailsViewModel)
    viewModelOf(::MovieDetailsViewModel)
    viewModelOf(::SeriesDetailsViewModel)

    viewModel { SeriesDetailsViewModel(get(),get()) }


}