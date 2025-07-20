package com.madrid.movio


import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.data.repositories.MovieDetailsRepositoryImpl
import com.madrid.data.repositories.RecommendedRepositoryImp
import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.local.LocalDataSourceImpl
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.MovieDetailsRepository
import com.madrid.domain.repository.RecommendedRepository
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.usecase.GetExploreMoreMovieUseCase
import com.madrid.domain.usecase.GetRecommendedMovieUseCase
import com.madrid.domain.usecase.mediaDeatailsUseCase.MovieDetailsUseCase
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.SeeAllForYou.SeeAllForYouViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsViewModel
import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
    single<RecommendedRepository> { RecommendedRepositoryImp(get(),get()) }
    single <MovieDetailsRepository>{ MovieDetailsRepositoryImpl(get(),get()) }


    single { MovioDatabase.getInstance(androidContext()) }


    // presentation
    viewModel {
        SearchViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        DetailsMovieViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        SeeAllForYouViewModel(
            get(),
            get(),
        )
    }
    viewModel {
        MovieDetailsViewModel(
            get(),
            get(),
        )
    }

    //domain
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    single { PreferredMediaUseCase(get()) }
    single { RecentSearchUseCase(get()) }
    single { TrendingMediaUseCase(get()) }
    single{ GetExploreMoreMovieUseCase(get()) }
    single { GetRecommendedMovieUseCase(get()) }
    single { MovieDetailsUseCase(get()) }

    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
