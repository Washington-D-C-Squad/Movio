package com.madrid.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.madrid.presentation.navigation.route.homeRoute
import com.madrid.presentation.navigation.route.libraryRoute
import com.madrid.presentation.navigation.route.moreRoute
import com.madrid.presentation.navigation.route.onBoardingRoute
import com.madrid.presentation.navigation.route.searchRoute
import com.madrid.presentation.navigation.route.splashRoute

@Composable
fun MovioNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        splashRoute(navController)
        onBoardingRoute(navController)
        authNavGraph(navController)
        homeRoute(navController)
        searchRoute(navController)
        libraryRoute(navController)
        moreRoute(navController)

    }
}