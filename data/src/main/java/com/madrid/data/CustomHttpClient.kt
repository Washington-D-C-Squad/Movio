package com.madrid.data

import com.madrid.data.BuildConfig.API_KEY
import com.madrid.data.BuildConfig.BASE_URL
import com.madrid.data.dataSource.remote.utils.Constants.KEY
import com.madrid.data.dataSource.remote.utils.Constants.PAGE
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
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
            defaultRequest {
                header("accept", "application/json")

            }
        }.get {
            url {
                protocol = HTTPS
                host = BASE_URL
                parameters.append("language", "en-US")
                parameters.append(PAGE, "1")
                parameters.append(KEY, API_KEY)
                apply(urlBuilder)
            }
        }

    }

}