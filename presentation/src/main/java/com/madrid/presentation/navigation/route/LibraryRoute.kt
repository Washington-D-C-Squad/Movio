package com.example.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.navigation.Screen
import com.madrid.presentation.navigation.fakeScreen.FakeLibraryScreen

fun NavGraphBuilder.libraryRoute(navController: NavController) {
    composable(route = Screen.Library.route) {
        FakeLibraryScreen()
    }
}