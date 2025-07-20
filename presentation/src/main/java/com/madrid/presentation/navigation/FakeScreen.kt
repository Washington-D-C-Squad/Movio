package com.madrid.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.madrid.designSystem.AppTheme
import com.madrid.designSystem.component.MovioText

@Composable
fun FakeHomeScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface),
        contentAlignment = Alignment.Center
    ) {
        MovioText(
            text = "Home Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}

@Composable
fun FakeSearchScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "Search Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}

@Composable
fun FakeMoreScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "More Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}

@Composable
fun FakeLibraryScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface),
        contentAlignment = Alignment.Center

    ) {
        MovioText(
            text = "Library Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}