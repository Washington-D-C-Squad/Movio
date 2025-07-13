package com.example.presentation.navigation

sealed class Screen(val route:String) {
    sealed class Authentication(val authRoute: String): Screen(authRoute){
        data object Login: Authentication("login")
        data object SignUp: Authentication("signUp")
        data object ForgetPassword: Authentication("forgetPassword")
    }
    data object AuthGraph : Screen("auth")
    data object Splash: Screen("splash")
    data object OnBoarding: Screen("onBoarding")
    data object Home: Screen("home")
    data object Search: Screen("search")
    data object Library: Screen("library")
    data object More: Screen("more")
}