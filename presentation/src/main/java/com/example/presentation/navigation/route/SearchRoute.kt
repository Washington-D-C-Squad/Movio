package com.example.presentation.navigation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.component.searchListUi.SearchScreen
import com.example.presentation.navigation.Screen
import com.example.presentation.navigation.fakeScreen.FakeSearchScreen

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(route = Screen.Search.route) {
        FakeSearchScreen()
    }
}