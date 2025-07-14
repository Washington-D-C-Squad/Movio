package com.madrid.movio

import com.madrid.data.dataSource.local.SearchLocalDataSource
import com.madrid.data.dataSource.local.SearchLocalSource
import com.madrid.data.dataSource.remote.SearchRemoteDataSource
import com.madrid.data.dataSource.remote.SearchRemoteSource
import com.madrid.data.repositories.SearchRepository
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.presentation.component.filteredImage.FilteredImageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single { SearchRepository(get()) }
    single<SearchRepository> { SearchRepository(get()) }
    single<SearchLocalSource> { SearchLocalDataSource() }
    single<SearchRemoteSource> { SearchRemoteDataSource() }

    // presentation
    viewModel { FilteredImageViewModel(get(), get()) }
    //domain
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
