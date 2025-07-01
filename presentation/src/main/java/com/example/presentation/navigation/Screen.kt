package com.example.presentation.navigation

sealed class Screen(val route:String) {
    object Splash: Screen("splash")
    object OnBoarding: Screen("onBoarding")
    object Home: Screen("home")
    object Search: Screen("search")
    object Library: Screen("library")
    object More: Screen("more")
}