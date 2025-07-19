package com.madrid.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun FakeHomeScreen() {
    Box (Modifier.fillMaxSize().background(AppTheme.colors.surfaceColor.surface)) {
        MovioText(
            text = "Home Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}
@Composable
fun FakeSearchScreen() {
    Box (Modifier.fillMaxSize().background(AppTheme.colors.surfaceColor.surface)) {
        MovioText(
            text = "Search Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}
@Composable
fun FakeMoreScreen() {
    Box (Modifier.fillMaxSize().background(AppTheme.colors.surfaceColor.surface)) {
        MovioText(
            text = "More Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}
@Composable
fun FakeLibraryScreen() {
    Box (Modifier.fillMaxSize().background(AppTheme.colors.surfaceColor.surface)) {
        MovioText(
            text = "Library Screen",
            textStyle = AppTheme.textStyle.title.largeBold14,
            color = AppTheme.colors.brandColors.primary,
        )
    }
}