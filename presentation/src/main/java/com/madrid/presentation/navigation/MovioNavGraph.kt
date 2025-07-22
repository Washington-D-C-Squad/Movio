package com.madrid.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.CustomBottomBar
import com.madrid.presentation.component.navBarDestinations

val LocalNavController = compositionLocalOf<NavHostController> { error("No Nav Controller Found") }


@Composable
fun MovioNavGraph(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val currentDestination = navBarDestinations.find { destinationItem ->
        destinationItem.destination::class.qualifiedName == currentRoute
    } ?: navBarDestinations.first()


    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = Theme.color.surfaces.surface
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MovioNavHost(navController)
        }

        CustomBottomBar(
            currentDestination = currentDestination.destination,
            navItems = navBarDestinations,
            onNavDestinationClicked = { destination ->
                navController.navigate(destination)
            },
            modifier = Modifier.navigationBarsPadding()
        )
    }
}