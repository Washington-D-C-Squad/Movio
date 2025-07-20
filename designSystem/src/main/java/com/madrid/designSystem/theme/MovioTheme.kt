package com.madrid.designSystem.theme

import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import com.madrid.designSystem.color.LocalMovioColor
import com.madrid.designSystem.color.darkThemeColors
import com.madrid.designSystem.color.lightThemeColors
import com.madrid.designSystem.text_style.LocalMovioTextStyle
import com.madrid.designSystem.text_style.defaultTextStyle

@Composable
fun MovioTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    UpdateStatusBarIconsForTheme(isDarkTheme)
    val theme = if (isDarkTheme) darkThemeColors else lightThemeColors

    CompositionLocalProvider(
        LocalMovioColor provides theme,
        LocalMovioTextStyle provides defaultTextStyle,

        ) {
        content()
    }
}

@Composable
private fun UpdateStatusBarIconsForTheme(darkTheme: Boolean) {
    val isDarkIcons = !darkTheme
    val view = LocalView.current
    val window = (view.context as? ComponentActivity)?.window ?: return
    WindowInsetsControllerCompat(window, view).apply {
        isAppearanceLightStatusBars = isDarkIcons
    }
}