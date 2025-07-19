package com.madrid.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.madrid.presentation.screens.searchScreen.SearchScreen

@Composable
fun MovioNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Destinations.HomeScreen
    ) {
        composable<Destinations.SplashScreen> {
            //call SplashScreen()
        }
        composable<Destinations.OnBoarding> {
            //call OnBoarding()
        }
        composable<Destinations.AuthenticationScreen> {
            //call AuthenticationScreen()
        }
        composable<Destinations.SearchScreen> {
            SearchScreen()
        }
        composable<Destinations.HomeScreen> {
           FakeHomeScreen()
        }
        composable<Destinations.MovieDetailsScreen> { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: "1"
            // Using demo screen for testing
            com.madrid.presentation.screens.MovieDetailsDemoScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<Destinations.SeriesDetailsScreen> {
            //call SeriesDetailsScreen()
        }
        composable<Destinations.TopCastScreen> {
            //call TopCastScreen()
        }
        composable<Destinations.ReviewsScreen> {
            //call ReviewsScreen()
        }
        composable<Destinations.SeasonsScreen> {
            //call SeasonsScreen()
        }
        composable<Destinations.EpisodesScreen> {
            //call EpisodesScreen()
        }

    }
}