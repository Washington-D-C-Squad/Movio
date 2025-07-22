package com.madrid.data.dataSource.remote.utils

import com.madrid.domain.exceptions.InvalidRequestException
import com.madrid.domain.exceptions.ServerErrorException
import com.madrid.domain.exceptions.TimeoutException

import com.madrid.domain.exceptions.UnauthorizedException
import com.madrid.domain.exceptions.UnknownException
import okhttp3.Response

inline fun responseWrapper(
    response: Response,
): Response {
    val redirectRangeException = 300..399
    val invalidRequestCodeException = 400
    val unauthorizedRangeException = 401..403
    val timeoutCodeException = 408
    val serverErrorRangeException = 500..599

    try {
        return response
    } catch (e: Exception) {
        when (response.code) {
            in redirectRangeException -> throw UnknownException(message = "Redirect Error: ${e.message}")
            invalidRequestCodeException -> throw InvalidRequestException(message = "Invalid Request Method: ${e.message}")
            in unauthorizedRangeException-> throw UnauthorizedException()
            timeoutCodeException -> throw TimeoutException(
                message = "Timeout Error: ${e.message}"
            )
            in serverErrorRangeException ->{
                throw ServerErrorException()
            }

            else -> throw UnknownException(e.message.toString())
        }
    }
}
