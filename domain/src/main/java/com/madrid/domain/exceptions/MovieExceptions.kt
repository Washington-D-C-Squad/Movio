package com.madrid.domain.exceptions

open class MovioExceptions(message: String) : Exception(message)


class UnknownException(message: String) : MovioExceptions(message)

open class AuthorizationException(message: String) : MovioExceptions(message)
class UnauthorizedException : AuthorizationException(message = "Unauthorized")
class ValidationException : AuthorizationException(message = "Validation")

open class ServerException(message: String) : MovioExceptions(message)
class ServerErrorException : ServerException(message = "Server Error")
class InvalidRequestMethodException : ServerException(message = "Invalid Request Method")
class InvalidResponseException : ServerException(message = "Invalid Response")
class TimeoutException : ServerException(message = "Timeout")
class NotFoundException : ServerException(message = "Not Found")

open class NetworkException(message: String) : MovioExceptions(message)
class NetworkErrorException : NetworkException(message = "Network")
