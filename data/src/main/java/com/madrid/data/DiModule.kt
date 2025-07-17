package com.madrid.data

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
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            defaultRequest {
                header("accept", "application/json")
                header("Content-type", "application/json")
                header("Authorization", "Bearer ${BuildConfig.API_KEY}")
                header(PAGE, "1")
                url(BuildConfig.BASE_URL)
            }
        }
    }
    single<HttpClientEngine> { CIO.create() }
    single<HttpClientConfig<CIOEngineConfig>> { HttpClientConfig() }
}