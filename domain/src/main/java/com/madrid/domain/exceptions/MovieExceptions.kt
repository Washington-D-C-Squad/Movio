package com.madrid.domain.exceptions

open class MovioExceptions(message: String) : Exception(message)


class UnknownException(message: String) : MovioExceptions(message)

// Authorization exceptions
open class AuthorizationException(message: String) : MovioExceptions(message)
class UnauthorizedException : AuthorizationException(message = "Unauthorized")
class ValidationException : AuthorizationException(message = "Validation")

// Remote source exceptions
open class ServerException(message: String) : MovioExceptions(message)
class ServerErrorException : ServerException(message = "Server Error")
class InvalidRequestException(message: String) :
    ServerException(message = "Invalid Request Method: $message")

class InvalidResponseException(message: String) : ServerException(message = "Invalid Response: $message")
class TimeoutException(message: String) : ServerException(message = "Timeout Error: $message")

open class NetworkException(message: String) : MovioExceptions(message)
class NetworkErrorException(message: String) : NetworkException(message = "Network Error: $message")

// Domain exceptions
class NotFoundException(message: String) : MovioExceptions(message = "Not Found: $message")