package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.navigation.route.forgetPasswordRoute
import com.example.presentation.navigation.route.loginRoute
import com.example.presentation.navigation.route.signUpRoute
import com.example.presentation.navigation.route.splashRoute

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        splashRoute(navController)
        loginRoute(navController)
        forgetPasswordRoute(navController)
        signUpRoute(navController)
    }
}