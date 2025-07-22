package com.madrid.movio


import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.data.repositories.MovieDetailsRepositoryImpl
import com.madrid.data.repositories.RecommendedRepositoryImp
import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.dataSource.local.LocalDataSourceImpl
import com.madrid.data.repositories.SeriesDetailsRepositoryImpl
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.MovieDetailsRepository
import com.madrid.domain.repository.RecommendedRepository
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.repository.SeriesDetailsRepository
import com.madrid.domain.usecase.GetExploreMoreMovieUseCase
import com.madrid.domain.usecase.GetRecommendedMovieUseCase
import com.madrid.domain.usecase.mediaDeatailsUseCase.MovieDetailsUseCase
import com.madrid.domain.usecase.mediaDeatailsUseCase.SeriesDetailsUseCase
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MovieUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.SeriesUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.SeeAllForYou.SeeAllForYouViewModel
import com.madrid.presentation.viewModel.detailsViewModel.DetailsMovieViewModel
import com.madrid.presentation.viewModel.detailsViewModel.MovieDetailsViewModel
import com.madrid.presentation.viewModel.detailsViewModel.Series
import com.madrid.presentation.viewModel.detailsViewModel.SeriesDetailsViewModel
import com.madrid.presentation.viewModel.searchViewModel.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single { MovioDatabase.getInstance(androidContext()) }
    single { get<MovioDatabase>().movieDao() }
    single { get<MovioDatabase>().seriesDao() }
    single { get<MovioDatabase>().artistDao() }
    single { get<MovioDatabase>().categoryDao() }
    single { get<MovioDatabase>().recentSearchDao() }
    single<LocalDataSource> { LocalDataSourceImpl(get(), get(), get(), get(), get()) }
    single<RecommendedRepository> { RecommendedRepositoryImp(get(), get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get(), get()) }
    single<SeriesDetailsRepository> { SeriesDetailsRepositoryImpl(get()) }


    // presentation
    viewModel {
        SearchViewModel(
            get(),
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
    viewModel {
        SeriesDetailsViewModel(
            get(),
            get(),
        )
    }

    //domain
    single { ArtistUseCase(get()) }
    single { MovieUseCase(get()) }
    single { SeriesUseCase(get()) }
    single { PreferredMediaUseCase(get()) }
    single { RecentSearchUseCase(get()) }
    single { TrendingMediaUseCase(get()) }
    single { GetExploreMoreMovieUseCase(get()) }
    single { GetRecommendedMovieUseCase(get()) }
    single { MovieDetailsUseCase(get()) }
    single { SeriesDetailsUseCase(get()) }

    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
