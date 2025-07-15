package com.madrid.movio


import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.data.dataSource.local.SearchLocalDataSource
import com.madrid.data.dataSource.remote.search.SearchRemoteSourceImpl
import com.madrid.data.repositories.SearchLocalSource
import com.madrid.data.repositories.SearchRemoteSource
import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.presentation.screens.searchScreen.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<SearchRemoteSource> { SearchRemoteSourceImpl() }

    single { MovioDatabase.getInstance(androidContext()) }
    single<SearchLocalSource> { SearchLocalDataSource(get()) }

    // presentation
    viewModel { SearchViewModel(get()) }


    //domain
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
