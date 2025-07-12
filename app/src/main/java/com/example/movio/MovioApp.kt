package com.example.movio

import android.app.Application
import com.example.data.local.dataModule
import com.example.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovioApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovioApp)
            modules(dataModule, presentationModule)
        }
    }
} 