package com.example.movio

import com.example.data.dataSource.local.SearchLocalDataSource
import com.example.data.dataSource.local.SearchLocalSource
import com.example.data.dataSource.remote.SearchRemoteDataSource
import com.example.data.dataSource.remote.SearchRemoteSource
import com.example.data.repositories.SearchRepository
import com.example.detectImageContent.GetImageBitmap
import com.example.detectImageContent.SensitiveContentDetection
import com.example.presentation.component.filteredImage.FilteredImageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepository(get()) }
    single<SearchRepository> { SearchRepository(get()) }
    single<SearchLocalSource> { SearchLocalDataSource() }
    single<SearchRemoteSource> { SearchRemoteDataSource() }

    // presentation
    viewModel { FilteredImageViewModel(get(), get()) }

    //domain
    single { SearchArtistUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
    single { SearchSeriesUseCase(get()) }

    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
