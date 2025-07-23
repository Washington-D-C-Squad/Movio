package com.madrid.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.madrid.presentation.screens.SeeAllForYou.SeeAllForYouScreen
import com.madrid.presentation.screens.detailsScreen.castDetails.ActorDetails
import com.madrid.presentation.screens.detailsScreen.castDetails.TopCastDetailsScreen
import com.madrid.presentation.screens.detailsScreen.detailsMovieScreen.MovieDetailsScreen
import com.madrid.presentation.screens.searchScreen.SearchScreen

@Composable
fun MovioNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HomeScreen,
        enterTransition = {
            fadeIn(tween(0))
        },
        exitTransition = {
            fadeOut(tween(0))
        }
    ) {
        composable<Destinations.SeeAllForYouScreen> {
            SeeAllForYouScreen()
        }
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
            FakeHomeScreen(

            )
        }
        composable<Destinations.MovieDetailsScreen> { backStackEntry ->
            MovieDetailsScreen()
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
        composable<Destinations.LibraryScreen> {
            FakeLibraryScreen()
        }
        composable<Destinations.MoreScreen> {
            FakeMoreScreen()
        }
        composable<Destinations.ActorDetails> {
           ActorDetails()
        }
        composable<Destinations.TopCastScreen> {
            TopCastDetailsScreen()
        }

    }
}