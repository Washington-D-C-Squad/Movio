package com.madrid.designsystem

import MovioColors
import MovioTextStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import com.madrid.designsystem.spacing.MovioSpacing
import com.madrid.designsystem.spacing.defaultSpacing
import dark
import defaultTextStyle
import light

@Stable
object AppTheme {

    val textStyle: MovioTextStyle
        @Composable @ReadOnlyComposable get() = LocalMovioTextStyle.current

    val colors: MovioColors
        @Composable @ReadOnlyComposable get() = LocalMovioColors.current
    val spacing: MovioSpacing
        @Composable @ReadOnlyComposable get() = LocalMovioSpacing.current

    @Composable
    operator fun invoke(
        useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
    ) {
        val colors = if (!useDarkTheme) {
            light
        } else {
            dark
        }

        CompositionLocalProvider(
            LocalMovioTextStyle provides defaultTextStyle,
            LocalMovioColors provides colors,
            LocalMovioSpacing provides defaultSpacing,
        ) {
            content()
        }

    }

    private val LocalMovioColors = staticCompositionLocalOf { light }
    private val LocalMovioTextStyle = staticCompositionLocalOf { defaultTextStyle }
    private val LocalMovioSpacing = staticCompositionLocalOf { defaultSpacing }
}