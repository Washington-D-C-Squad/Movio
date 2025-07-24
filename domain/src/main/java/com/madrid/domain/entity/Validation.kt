package com.madrid.domain.entity

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(
        val errorType: ValidationErrorType,
        val errorMessage: String
    ) : ValidationResult()
}

enum class ValidationErrorType {
    EMPTY_USERNAME,
    EMPTY_PASSWORD,
    INVALID_EMAIL_FORMAT,
    WEAK_PASSWORD,
    USERNAME_TOO_SHORT
}