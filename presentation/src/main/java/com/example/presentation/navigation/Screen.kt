package com.example.presentation.navigation

sealed class Screen(val route:String) {
    sealed class Authentication(val authRoute: String): Screen(authRoute){
        object Login: Authentication("login")
        object SignUp: Authentication("signUp")
        object ForgetPassword: Authentication("forgetPassword")
    }
    object Splash: Screen("splash")
    object OnBoarding: Screen("onBoarding")
    object Home: Screen("home")
    object Search: Screen("search")
    object Library: Screen("library")
    object More: Screen("more")
}