package com.madrid.data.dataSource.remote.utils

import com.madrid.domain.exceptions.InvalidRequestException
import com.madrid.domain.exceptions.ServerErrorException
import com.madrid.domain.exceptions.TimeoutException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse
import com.madrid.domain.exceptions.UnauthorizedException
import com.madrid.domain.exceptions.UnknownException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

suspend inline fun <reified T> responseWrapper(
    client: HttpClient,
    response: HttpClient.() -> HttpResponse,
): T {
    try {
        return client.response().body()
    } catch (e: ClientRequestException) {
        when (e.response.status.value) {
            400 -> throw InvalidRequestException(message = "Invalid Request Method: ${e.message}")
            403 -> throw UnauthorizedException()
            408 -> throw TimeoutException(
                message = "Timeout Error: ${e.message}"
            )

            else -> throw UnknownException(e.message)
        }
    } catch (e: Exception) {
        throw UnknownException(message = "Unknown error occurred: ${e.message}")
    }
}
