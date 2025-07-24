package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.screens.moreScreen.MoreScreen

fun NavGraphBuilder.moreRoute(navController: NavController) {
    composable(route = Screen.More.route) {
        MoreScreen()
    }
}