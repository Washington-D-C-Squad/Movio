package com.madrid.movio

import com.madrid.data.dataSource.remote.SearchRemoteDataSource
import com.madrid.data.dataSource.remote.SearchRemoteSource
import com.madrid.data.repositories.SearchRepository
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.RecentSearchRepository
import com.madrid.domain.searchUseCase.SearchArtistUseCase
import com.madrid.domain.searchUseCase.SearchMoviesUseCase
import com.madrid.domain.searchUseCase.SearchSeriesUseCase
import com.madrid.presentation.component.filteredImage.FilteredImageViewModel
import com.madrid.presentation.component.screens.searchscreen.SearchListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<RecentSearchRepository> { SearchRepository(get()) }
    single<SearchRepository> { SearchRepository(get()) }
    single<SearchLocalSource> { SearchLocalDataSource() }
    single<SearchRemoteSource> { SearchRemoteDataSource() }

    // presentation
    viewModel { SearchListViewModel(get()) }
    viewModel { FilteredImageViewModel(get(), get()) }
    //domain
    single { SearchArtistUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
    single { SearchSeriesUseCase(get()) }
    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
