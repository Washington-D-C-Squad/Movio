package com.madrid.movio.di


import com.madrid.data.roomModule
import org.koin.dsl.module

val AppModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule,
        roomModule
    )
}
