package com.madrid.movio


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
import com.madrid.domain.usecase.searchUseCase.PreferredMediaUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.domain.usecase.searchUseCase.TrendingMediaUseCase
import com.example.presentation.viewModel.base.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app = module {

    // data
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<SearchLocalSource> { SearchLocalDataSource(androidContext()) }
    single<SearchRemoteSource> { SearchRemoteSourceImpl() }

    // use cases
    single { ArtistUseCase(get()) }
    single { MediaUseCase(get()) }
    single { PreferredMediaUseCase(get()) }
    single { RecentSearchUseCase(get()) }
    single { TrendingMediaUseCase(get()) }

    // presentation
    viewModel { SearchViewModel(get(), get(), get(), get(), get()) }

    // detectImageContent
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}
