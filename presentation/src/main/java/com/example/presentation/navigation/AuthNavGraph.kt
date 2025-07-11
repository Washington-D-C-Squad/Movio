package com.example.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.presentation.navigation.route.forgetPasswordRoute
import com.example.presentation.navigation.route.loginRoute
import com.example.presentation.navigation.route.signUpRoute
import com.example.presentation.navigation.route.splashRoute

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Screen.Authentication.Login.route,
        startDestination = Screen.Authentication.Login.route,
    ) {
        loginRoute(navController)
        forgetPasswordRoute(navController)
        signUpRoute(navController)
    }
}