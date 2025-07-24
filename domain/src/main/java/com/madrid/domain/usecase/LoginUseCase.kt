package com.madrid.domain.usecase

import com.madrid.domain.entity.AuthErrorType
import com.madrid.domain.entity.LoginResult
import com.madrid.domain.entity.User
import com.madrid.domain.entity.ValidationResult
import com.madrid.domain.entity.ValidationErrorType
import com.madrid.domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(username: String, password: String): LoginResult {
        return try {
            val validation = validateCredentials(username, password)
            if (validation is ValidationResult.Invalid) {
                return LoginResult.Error(
                    errorType = AuthErrorType.INVALID_CREDENTIALS,
                    errorMessage = validation.errorMessage
                )
            }

            val user = userRepository.login(username, password)
            if (user.twoFactorEnabled) {
                LoginResult.Success(user, requiresTwoFactorAuth = true)
            } else {
                LoginResult.Success(user)
            }
        } catch (e: Exception) {
            LoginResult.Error(
                errorType = AuthErrorType.INVALID_CREDENTIALS,
                errorMessage = e.message ?: "Login failed"
            )
        }
    }

    suspend fun verifyTwoFactorCode(code: String): LoginResult {
        return try {
            val user = userRepository.verifyTwoFactorCode(code)
            LoginResult.Success(user)
        } catch (e: Exception) {
            LoginResult.Error(
                errorType = AuthErrorType.INVALID_2FA_CODE,
                errorMessage = "Invalid verification code"
            )
        }
    }

    suspend fun loginAsGuest(): User {
        return userRepository.loginAsGuest()
    }

    fun validateCredentials(username: String, password: String): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult.Invalid(
                ValidationErrorType.EMPTY_USERNAME,
                "Username cannot be empty"
            )
            username.length < 3 -> ValidationResult.Invalid(
                ValidationErrorType.USERNAME_TOO_SHORT,
                "Username must be at least 3 characters"
            )
            password.isBlank() -> ValidationResult.Invalid(
                ValidationErrorType.EMPTY_PASSWORD,
                "Password cannot be empty"
            )
            password.length < 6 -> ValidationResult.Invalid(
                ValidationErrorType.WEAK_PASSWORD,
                "Password must be at least 6 characters"
            )
            else -> ValidationResult.Valid
        }
    }

    suspend fun checkActiveSession(): Boolean {
        return userRepository.getCurrentUser()?.let { user ->
            user.authToken != null && !userRepository.isTokenExpired(user.authToken)
        } ?: false
    }
}