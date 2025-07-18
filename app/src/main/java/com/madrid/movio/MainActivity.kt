package com.madrid.movio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.composables.CustomBottomBar
import com.madrid.presentation.composables.navBarDestinations
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.navigation.MovioNavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val currentDestination = navBarDestinations.find { destinationItem ->
        destinationItem.destination::class.qualifiedName == currentRoute
    } ?: navBarDestinations.first()

    CompositionLocalProvider(LocalNavController provides navController) {
        Column(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MovioNavGraph(navController)
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
}

