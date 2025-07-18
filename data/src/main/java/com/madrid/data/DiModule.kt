package com.madrid.data

import com.madrid.data.dataSource.remote.RemoteDataSourceImpl
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val roomModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single { CustomHttpClient() }
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get()) }
    single<HttpClientEngine> { CIO.create() }
    single<HttpClientConfig<CIOEngineConfig>> { HttpClientConfig() }
}