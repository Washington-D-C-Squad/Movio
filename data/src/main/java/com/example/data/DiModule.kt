package com.example.data.local

import android.app.Application
import androidx.room.Room
import com.example.data.dataSource.local.SearchLocalDataSource
import com.example.data.dataSource.local.SearchLocalSource
import com.example.data.repositories.SearchRepository
import org.koin.dsl.module
import com.example.domain.RecentSearchRepository

val dataModule = module {
    single { Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "app_db").build() }
    single<SearchLocalSource> { SearchLocalDataSource() }
    single<RecentSearchRepository> { SearchRepository() }
} 