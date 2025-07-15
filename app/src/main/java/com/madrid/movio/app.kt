package com.madrid.movio

import com.madrid.data.dataSource.local.SearchLocalDataSource
import com.madrid.data.repositories.SearchLocalSource
import com.madrid.data.repositories.SearchRepositoryImpl

import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.component.filteredImage.FilteredImageViewModel
import com.madrid.presentation.component.screens.searchScreen.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<SearchLocalSource> { SearchLocalDataSource(get()) }

    // presentation
    viewModel { FilteredImageViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    
    //domain
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    single { RecentSearchUseCase(get()) }
    
    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
