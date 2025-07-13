package com.example.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.navigation.Screen
import com.example.presentation.navigation.fakeScreen.FakeLibraryScreen

fun NavGraphBuilder.libraryRoute(navController: NavController) {
    composable(route = Screen.Library.route) {
        FakeLibraryScreen()
    }
}