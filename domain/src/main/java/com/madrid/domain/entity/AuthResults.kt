package com.madrid.domain.entity



sealed class LoginResult {
    data class Success(
        val user: User,
        val requiresTwoFactorAuth: Boolean = false
    ) : LoginResult()

    data class Error(
        val errorType: AuthErrorType,
        val errorMessage: String? = null,
        val remainingAttempts: Int? = null
    ) : LoginResult()
}

enum class AuthErrorType {
    INVALID_CREDENTIALS,
    ACCOUNT_LOCKED,
    NETWORK_ERROR,
    SERVER_ERROR,
    TWO_FACTOR_REQUIRED,
    INVALID_2FA_CODE,
    EXPIRED_TOKEN
}