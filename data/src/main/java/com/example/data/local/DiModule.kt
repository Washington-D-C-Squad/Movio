package com.example.data.local

import androidx.room.Room
import android.app.Application
import com.example.data.dataSource.local.SearchLocalDataSource
import com.example.data.dataSource.local.SearchLocalSource
import com.example.data.repositories.SearchRepository
import org.koin.dsl.module
import com.example.domain.interfaces.SearchRepository as DomainSearchRepository

val dataModule = module {
    single { Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "app_db").build() }
    single<SearchLocalSource> { SearchLocalDataSource(get()) }
    single<DomainSearchRepository> { SearchRepository(get()) }
} 