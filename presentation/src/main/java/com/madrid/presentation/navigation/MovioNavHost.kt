package com.madrid.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.madrid.presentation.navigation.Destinations.SeeAllForYouScreen
import com.madrid.presentation.navigation.Destinations.SeeAllSimilarMoviesScreen
import com.madrid.presentation.navigation.Destinations.TopCastScreen
import com.madrid.presentation.screens.detailsScreen.castDetails.TopCastScreenList
import com.madrid.presentation.screens.detailsScreen.componant.RatingMovieBottomSheet
import com.madrid.presentation.screens.detailsScreen.componant.SaveMovieBottomSheet
import com.madrid.presentation.screens.detailsScreen.componant.ShareBottomSheet
import com.madrid.presentation.screens.detailsScreen.detailsMovieScreen.MovieDetailsScreen
import com.madrid.presentation.screens.detailsScreen.detailsMovieScreen.SeeAllSimilarMoviesScreen
import com.madrid.presentation.screens.detailsScreen.reviewsScreen.composables.ReviewScreen
import com.madrid.presentation.screens.detailsScreen.seriesDetails.EpisodesScreen
import com.madrid.presentation.screens.detailsScreen.seriesDetails.SeasonsScreen
import com.madrid.presentation.screens.detailsScreen.seriesDetails.SeriesDetailsScreen
import com.madrid.presentation.screens.searchScreen.SearchScreen
import com.madrid.presentation.screens.searchScreen.SeeAllForYou.SeeAllForYouScreen
import com.madrid.presentation.viewModel.detailsViewModel.ReviewsScreenUiState

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
        composable<Destinations.SeeAllSimilarMoviesScreen> { backStackEntry ->
            val movieId = backStackEntry.toRoute<Destinations.SeeAllSimilarMoviesScreen>().movieId
            SeeAllSimilarMoviesScreen(movieId = movieId)
        }

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
        composable<Destinations.EpisodesScreen> {
            EpisodesScreen()
        }

        composable<Destinations.MovieDetailsScreen> {
            MovieDetailsScreen()
        }
        composable<Destinations.SeriesDetailsScreen> {
            SeriesDetailsScreen()
        }
        composable<Destinations.TopCastScreen> {
            TopCastScreenList()
        }
        composable<Destinations.ReviewsScreen> {
            ReviewScreen(
                uiState = ReviewsScreenUiState()
            )
        }
        composable<Destinations.SeasonsScreen> {
            SeasonsScreen()
        }
        composable<Destinations.LibraryScreen> {
            FakeLibraryScreen()
        }
        composable<Destinations.MoreScreen> {
            FakeMoreScreen()
        }
        dialog<Destinations.RatingMovieBottomSheetDestination> { navBackStackEntry ->
            val args = navBackStackEntry.arguments?.let {
                Pair(
                    it.getString("imageUrl") ?: "",
                    it.getString("nameMovie") ?: ""
                )
            } ?: Pair("", "")

            RatingMovieBottomSheet(
                show = true,
                onDismiss = { navController.popBackStack() },
                imageUrl = args.first,
                nameMovie = args.second
            )
        }

        dialog<Destinations.SaveMovieBottomSheetDestination> {
            SaveMovieBottomSheet(
                show = true,
                onDismiss = { navController.popBackStack() }
            )
        }

        dialog<Destinations.ShareBottomSheetDestination> {
            ShareBottomSheet(
                show = true,
                onDismiss = { navController.popBackStack() }
            )
        }

    }
}

