package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.navigation.route.homeRoute
import com.example.presentation.navigation.route.libraryRoute
import com.example.presentation.navigation.route.moreRoute
import com.example.presentation.navigation.route.onBoardingRoute
import com.example.presentation.navigation.route.searchRoute
import com.example.presentation.navigation.route.splashRoute

@Composable
fun MovioNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        splashRoute(navController)
        onBoardingRoute(navController)
        homeRoute(navController)
        searchRoute(navController)
        libraryRoute(navController)
        moreRoute(navController)

    }
}