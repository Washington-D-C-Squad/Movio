package com.madrid.movio

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.madrid.designsystem.AppTheme
import com.madrid.presentation.composables.CustomBottomBar
import com.madrid.presentation.composables.navDestinations
import com.madrid.presentation.navigation.MovioNavGraph
import com.madrid.presentation.navigation.Screen
import com.madrid.presentation.screens.searchScreen.FilteredScreen
import com.madrid.presentation.screens.searchScreen.SearchScreen


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
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    Column (Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MovioNavGraph(navController = navController)
        }
        CustomBottomBar(
            currentRoute = currentRoute ?: Screen.Home.route,
            onNavDestinationClicked = { navController.navigate(it) },
            navItems = navDestinations,
        )
    }
}