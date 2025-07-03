package com.example.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.navigation.Screen

fun NavGraphBuilder.forgetPasswordRoute(navController: NavController) {
    composable(route = Screen.Authentication.ForgetPassword.route) {

    }
}