package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.signUpRoute(navController: NavController) {
    composable(route = Screen.Authentication.SignUp.route) {

    }
}