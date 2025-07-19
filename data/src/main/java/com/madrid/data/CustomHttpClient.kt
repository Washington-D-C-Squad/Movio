package com.madrid.data

import android.util.Log
import com.madrid.data.BuildConfig.API_KEY
import com.madrid.data.BuildConfig.BASE_URL
import com.madrid.data.dataSource.remote.utils.Constants.KEY
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol.Companion.HTTPS

class CustomHttpClient {
    suspend fun buildHttpClient(
        urlBuilder: URLBuilder.() -> Unit
    ): HttpResponse {
        return HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("KTOR", "HTTP Client: $message")
                    }
                }
            }
            defaultRequest {
                header("accept", "application/json")

            }
        }.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                parameters.append("language", "en-US")
                parameters.append(KEY, API_KEY)
                apply(urlBuilder)
            }
        }

    }

}