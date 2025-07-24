package com.madrid.presentation.viewModel.loginViewModel

import androidx.lifecycle.viewModelScope
import com.madrid.domain.entity.AuthErrorType
import com.madrid.domain.entity.LoginResult
import com.madrid.domain.entity.ValidationErrorType
import com.madrid.domain.entity.ValidationResult
import com.madrid.domain.usecase.LoginUseCase
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase
import com.madrid.presentation.viewModel.LoginError
import com.madrid.presentation.viewModel.LoginUiState
import com.madrid.presentation.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val recentSearchUseCase: RecentSearchUseCase
) : BaseViewModel<LoginUiState, Nothing>(LoginUiState()) {

    fun updateUsername(username: String) {
        updateState { it.copy(username = username, errorState = null) }
    }

    fun updatePassword(password: String) {
        updateState { it.copy(password = password, errorState = null) }
    }

    fun toggleShowPassword() {
        updateState { it.copy(showPassword = !it.showPassword) }
    }

    fun login(onSuccess: () -> Unit) {
        val current = currentState

        when (val validation = loginUseCase.validateCredentials(current.username, current.password)) {
            is ValidationResult.Invalid -> {
                updateState {
                    it.copy(errorState = LoginError.EmptyFields(
                        usernameEmpty = validation.errorType == ValidationErrorType.EMPTY_USERNAME,
                        passwordEmpty = validation.errorType == ValidationErrorType.EMPTY_PASSWORD
                    ))
                }
                return
            }
            ValidationResult.Valid -> {
                updateState { it.copy(isLoading = true) }

                viewModelScope.launch {
                    try {
                        when (val result = loginUseCase.execute(current.username, current.password)) {
                            is LoginResult.Success -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        loginSuccess = true,
                                        isGuest = false
                                    )
                                }
                                onSuccess()
                            }
                            is LoginResult.Error -> handleLoginError(result)
                        }
                    } catch (e: Exception) {
                        updateState {
                            it.copy(
                                isLoading = false,
                                errorState = LoginError.GenericError("An unexpected error occurred")
                            )
                        }
                    }
                }
            }
        }
    }

    fun loginAsGuest() {
        updateState { it.copy(isLoading = true, isGuest = true) }

        viewModelScope.launch {
            try {
                val user = loginUseCase.loginAsGuest()
                updateState {
                    it.copy(
                        isLoading = false,
                        loginSuccess = true,
                        isGuest = true
                    )
                }
            } catch (e: Exception) {
                updateState {
                    it.copy(
                        isLoading = false,
                        errorState = LoginError.GenericError("Failed to login as guest")
                    )
                }
            }
        }
    }

    private fun handleLoginError(result: LoginResult.Error) {
        val error = when (result.errorType) {
            AuthErrorType.INVALID_CREDENTIALS -> LoginError.InvalidCredentials
            AuthErrorType.ACCOUNT_LOCKED -> LoginError.AccountLocked
            AuthErrorType.NETWORK_ERROR -> LoginError.NetworkError
            else -> LoginError.GenericError(result.errorMessage ?: "An error occurred")
        }

        updateState {
            it.copy(
                isLoading = false,
                errorState = error
            )
        }
    }
}