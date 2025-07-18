package com.madrid.presentation.screens.SeeAllForYou

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val seeAllForYouScreenRoute = "seeAllForYouScreenRout"
fun NavGraphBuilder.seeAllForYouScreenRoute(
    navController: NavController
){
    composable(
        seeAllForYouScreenRoute
    ) {
        SeeAllForYouScreen(navController)
    }
}

fun NavController.navigateToSeeAllForYouScreenRoute(

) {
    navigate(seeAllForYouScreenRoute)
}