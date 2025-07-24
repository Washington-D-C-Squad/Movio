package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.screens.libraryScreen.LibraryScreen

fun NavGraphBuilder.libraryRoute(navController: NavController) {
    composable(route = Screen.Library.route) {
        LibraryScreen()
    }
}