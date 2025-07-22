package com.madrid.movio.di


import org.koin.dsl.module

val AppModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule,
        remoteModule
    )
}
