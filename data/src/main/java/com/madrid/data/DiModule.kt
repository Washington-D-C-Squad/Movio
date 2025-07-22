package com.madrid.data

import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.data.dataSource.remote.RemoteDataSourceImpl
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val roomModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get()) }
    single { Json { ignoreUnknownKeys = true } }
    single { CustomHttpClient() }
    single<HttpClientEngine> { CIO.create() }
    single<HttpClientConfig<CIOEngineConfig>> { HttpClientConfig() }
}