package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.screens.homeScreen.HomeScreen

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(route = Screen.Home.route) {
        HomeScreen()
    }
}