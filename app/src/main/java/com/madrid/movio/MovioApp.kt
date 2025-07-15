package com.madrid.movio

import android.app.Application
import com.madrid.data.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovioApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(app, roomModule)
        }
    }
} 