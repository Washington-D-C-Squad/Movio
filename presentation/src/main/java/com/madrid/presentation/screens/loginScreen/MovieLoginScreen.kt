package com.madrid.presentation.screens.loginScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.*
import com.madrid.designSystem.component.textInputField.BasicTextInputField
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.screens.loginScreen.component.*
import com.madrid.presentation.viewModel.LoginError
import com.madrid.presentation.viewModel.LoginUiState
import com.madrid.presentation.viewModel.loginViewModel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieLoginScreen(
    onLoginSuccess: () -> Unit = {},
    onOpenWebView: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onGuestLogin: () -> Unit = {}
) {
    val viewModel: LoginViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.loginSuccess) {
        if (state.loginSuccess) {
            if (state.isGuest) {
                onGuestLogin()
            } else {
                onLoginSuccess()
            }
        }
    }

    MovieLoginContent(
        state = state,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword,
        onLoginClick = { viewModel.login(onLoginSuccess) },
        onTogglePassword = viewModel::toggleShowPassword,
        onForgotPasswordClick = onForgotPasswordClick,
        onSignUpClick = onSignUpClick,
        onGuestLogin = viewModel::loginAsGuest
    )
}

@Composable
fun MovieLoginContent(
    state: LoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onTogglePassword: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onGuestLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        LoginHeader()

        BasicTextInputField(
            startIconPainter = painterResource(R.drawable.profile_circle),
            hintText = "Username",
            value = state.username,
            onValueChange = onUsernameChange,
            modifier = Modifier.padding(bottom = 12.dp),
            isError = state.errorState is LoginError.EmptyFields &&
                    (state.errorState as LoginError.EmptyFields).usernameEmpty,
            endIconPainter = null,
            errorBorderBrush = Theme.color.gradients.errorBorderGradient
        )

        BasicTextInputField(
            startIconPainter = painterResource(R.drawable.lock),
            hintText = "Password",
            value = state.password,
            onValueChange = onPasswordChange,
            visualTransformation = if (state.showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            endIconPainter = painterResource(
                if (state.showPassword) R.drawable.eye else R.drawable.eye_slash
            ),
            onClickEndIcon = onTogglePassword,
            modifier = Modifier.padding(bottom = 12.dp),
            isError = (state.errorState is LoginError.EmptyFields &&
                    (state.errorState as LoginError.EmptyFields).passwordEmpty) ||
                    state.errorState is LoginError.InvalidCredentials,
            errorBorderBrush = Theme.color.gradients.errorBorderGradient
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // بناء رسالة الخطأ الموحدة
            val errorMessage = buildString {
                when (val error = state.errorState) {
                    is LoginError.EmptyFields -> {
                        if (error.usernameEmpty) append("Username is required")
                        if (error.usernameEmpty && error.passwordEmpty) append(", ")
                        if (error.passwordEmpty) append("Password is required")
                    }
                    is LoginError.InvalidCredentials -> append("Invalid username or password")
                    is LoginError.AccountLocked -> append("Account locked. Contact support.")
                    is LoginError.NetworkError -> append("Network error. Try again.")
                    is LoginError.GenericError -> append(error.message)
                    else -> {}
                }
            }

            if (errorMessage.isNotEmpty()) {
                MovioIcon(painterResource(R.drawable.info_circle),
                    tint = Theme.color.system.onError,
                    contentDescription = null,
                     modifier = Modifier.padding(end = 4.dp))

                MovioText(
                    text = errorMessage,
                    textStyle = Theme.textStyle.label.mediumMedium12,
                    color = Theme.color.system.onError,
                    maxLines = 2,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            MovioText(
                text = "Forgot Password?",
                textStyle = Theme.textStyle.label.mediumMedium12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.clickable(onClick = onForgotPasswordClick)
            )
        }

        AnimatedLoginButton(
            isLoading = state.isLoading,
            onClick = onLoginClick,
            enabled = state.canLogin
        )

        Spacer(modifier = Modifier.height(40.dp))

        OrDivider()

        MovioButton(
            onClick = onGuestLogin,
            color = Theme.color.surfaces.onSurfaceAt3,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            MovioText(
                text = "Continue as a guest",
                textStyle = Theme.textStyle.label.smallRegular14,
                color = Theme.color.surfaces.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SignUpRow(onSignUpClick = onSignUpClick)
    }
}

@Composable
private fun SignUpRow(onSignUpClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        MovioText(
            text = "Don't have an account?",
            textStyle = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(4.dp))
        MovioText(
            text = "Sign up",
            textStyle = Theme.textStyle.label.mediumMedium14,
            color = Theme.color.brand.primary,
            modifier = Modifier.clickable(onClick = onSignUpClick)
        )
    }
}





@Composable
private fun ErrorText(text: String) {
    MovioText(
        text = text,
        textStyle = Theme.textStyle.label.mediumMedium12,
        color = Theme.color.system.error,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewFullMovieLoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var loginSuccess by remember { mutableStateOf(false) }
    var isGuest by remember { mutableStateOf(false) }
    var errorState by remember { mutableStateOf<LoginError?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val state = LoginUiState(
        username = username,
        password = password,
        showPassword = showPassword,
        errorState = errorState,
        loginSuccess = loginSuccess,
        isGuest = isGuest,
        isLoading = isLoading,
       // canLogin = username.isNotBlank() && password.isNotBlank()
    )

    val scope = rememberCoroutineScope()

    fun onLoginClick() {
        scope.launch {
            isLoading = true
            delay(2000)
            if (username == "user" && password == "pass") {
                loginSuccess = true
                isGuest = false
                errorState = null
            } else {
                errorState = LoginError.InvalidCredentials
            }
            isLoading = false
        }
    }

    fun onTogglePassword() {
        showPassword = !showPassword
    }

    fun onUsernameChange(newValue: String) {
        username = newValue
        errorState = null
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
        errorState = null
    }

    MovieLoginContent(
        state = state,
        onUsernameChange = ::onUsernameChange,
        onPasswordChange = ::onPasswordChange,
        onLoginClick = ::onLoginClick,
        onTogglePassword = ::onTogglePassword,
        onForgotPasswordClick = { },
        onSignUpClick = { },
        onGuestLogin = {
            loginSuccess = true
            isGuest = true
        }
    )
}
