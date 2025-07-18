package com.madrid.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madrid.presentation.navigation.FakeSearchScreen
import com.madrid.presentation.navigation.Screen
import com.madrid.presentation.screens.searchScreen.SearchScreen


fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(route = Screen.Search.route) {
        SearchScreen()
    }
}