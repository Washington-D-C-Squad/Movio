package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.navigation.FakeHomeScreen
import com.madrid.presentation.navigation.Screen

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(route = Screen.Home.route) {
        FakeHomeScreen()
    }
}