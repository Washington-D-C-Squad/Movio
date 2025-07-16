package com.madrid.movio


import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.presentation.screens.searchScreen.SearchViewModel
import com.madrid.data.dataSource.local.SearchLocalDataSource
import com.madrid.data.dataSource.remote.RemoteDataSourceImpl
import com.madrid.data.repositories.RemoteDataSource
import com.madrid.data.repositories.SearchLocalSource
import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import org.koin.android.ext.koin.androidContext
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<SearchRemoteSource> { SearchRemoteSourceImpl() }

    single { MovioDatabase.getInstance(androidContext()) }
    single<SearchLocalSource> { SearchLocalDataSource(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl() }

    // presentation
    viewModel {
        SearchViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }


    //domain
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    single{ PreferredMediaUseCase(get()) }
    single { RecentSearchUseCase(get()) }
    single { TrendingMediaUseCase(get()) }
    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
