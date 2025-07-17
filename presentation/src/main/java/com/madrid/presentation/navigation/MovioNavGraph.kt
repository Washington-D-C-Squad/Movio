package com.madrid.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.madrid.presentation.navigation.route.searchRoute
import com.madrid.presentation.navigation.route.homeRoute
import com.madrid.presentation.navigation.route.libraryRoute
import com.madrid.presentation.navigation.route.moreRoute
import com.madrid.presentation.navigation.route.onBoardingRoute
import com.madrid.presentation.navigation.route.splashRoute

val LocalNavController = compositionLocalOf<NavHostController> { error("No Nav Controller Found") }


@Composable
fun MovioNavGraph(navController: NavHostController) {
    MovioNavHost(navController)
}