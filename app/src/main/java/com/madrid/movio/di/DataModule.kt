package com.madrid.movio.di


import com.madrid.data.dataSource.local.LocalDataSourceImpl
import com.madrid.data.dataSource.local.MovioDatabase
import com.madrid.data.repositories.MovieDetailsRepositoryImpl
import com.madrid.data.repositories.RecommendedRepositoryImp
import com.madrid.data.repositories.SearchRepositoryImpl
import com.madrid.data.repositories.local.LocalDataSource
import com.madrid.detectImageContent.GetImageBitmap
import com.madrid.detectImageContent.SensitiveContentDetection
import com.madrid.domain.repository.MovieDetailsRepository
import com.madrid.domain.repository.RecommendedRepository
import com.madrid.domain.repository.SearchRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {
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
    single { GetImageBitmap(get()) }
    single { SensitiveContentDetection(get()) }
}