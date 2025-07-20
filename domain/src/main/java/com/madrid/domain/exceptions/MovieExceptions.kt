package com.madrid.domain.exceptions

open class MovioException(message: String) : Exception(message)


class UnknownException(message: String) : MovioException(message)

// Authorization exceptions
open class AuthorizationException(message: String) : MovioException(message)
class UnauthorizedException : AuthorizationException(message = "Unauthorized")
class ValidationException : AuthorizationException(message = "Validation")

// Remote source exceptions
open class ServerException(message: String) : MovioException(message)
class ServerErrorException : ServerException(message = "Server Error")
class InvalidRequestException(message: String) :
    ServerException(message = "Invalid Request Method: $message")

class InvalidResponseException(message: String) : ServerException(message = "Invalid Response: $message")
class TimeoutException(message: String) : ServerException(message = "Timeout Error: $message")

open class NetworkException(message: String) : MovioException(message)
class NetworkErrorException(message: String) : NetworkException(message = "Network Error: $message")

// Domain exceptions
class NotFoundException(message: String) : MovioException(message = "Not Found: $message")