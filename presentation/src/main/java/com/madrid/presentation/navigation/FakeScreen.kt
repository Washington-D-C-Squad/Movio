package com.madrid.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioText
import org.koin.compose.koinInject

@Composable
fun FakeHomeScreen(name: String = koinInject()) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        contentAlignment = Alignment.Center
    ) {
        MovioText(
            text = name,
            textStyle = Theme.textStyle.title.largeBold14,
            color = Theme.color.brand.primary,
        )
    }
}

@Composable
fun FakeSearchScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "Search Screen",
            textStyle = Theme.textStyle.title.largeBold14,
            color = Theme.color.brand.primary,
        )
    }
}

@Composable
fun FakeMoreScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "More Screen",
            textStyle = Theme.textStyle.title.largeBold14,
            color = Theme.color.brand.primary,
        )
    }
}

@Composable
fun FakeLibraryScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "Library Screen",
            textStyle = Theme.textStyle.title.largeBold14,
            color = Theme.color.brand.primary,
        )
    }
}