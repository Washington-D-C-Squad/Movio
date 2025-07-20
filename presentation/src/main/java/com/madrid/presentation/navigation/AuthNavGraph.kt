package com.madrid.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.madrid.presentation.navigation.route.Screen
import com.madrid.presentation.navigation.route.forgetPasswordRoute
import com.madrid.presentation.navigation.route.loginRoute
import com.madrid.presentation.navigation.route.signUpRoute

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Screen.AuthGraph.route,
        startDestination = Screen.Authentication.Login.route,
    ) {
        loginRoute(navController)
        forgetPasswordRoute(navController)
        signUpRoute(navController)
    }
}