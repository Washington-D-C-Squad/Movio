package com.madrid.movio


import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.data.repositories.local.LocalDataSourceImpl
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.data.repositories.remote.RemoteDataSourceImpl
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.SearchRepository
import com.madrid.domain.usecase.searchUseCase.ArtistUseCase
import com.madrid.domain.usecase.searchUseCase.MediaUseCase
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.madrid.presentation.screens.searchScreen.viewModel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get()) }

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
