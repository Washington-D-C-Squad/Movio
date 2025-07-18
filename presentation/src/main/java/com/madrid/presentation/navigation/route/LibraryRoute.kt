package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.navigation.FakeLibraryScreen
import com.madrid.presentation.navigation.Screen

fun NavGraphBuilder.libraryRoute(navController: NavController) {
    composable(route = Screen.Library.route) {
        FakeLibraryScreen()
    }
}