package com.example.data.local

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "app_db").build() }
    single { get<AppDatabase>().artistResultDao() }

} 