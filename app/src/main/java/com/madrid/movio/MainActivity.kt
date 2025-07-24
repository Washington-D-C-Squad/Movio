package com.madrid.movio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.presentation.navigation.LocalNavController
import com.madrid.presentation.navigation.MovioNavGraph
import com.madrid.presentation.screens.TrendingLayout


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovioTheme {
               // MainScreen()
                TrendingLayout()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        MovioNavGraph(navController)
    }
}

