package com.madrid.designSystem.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val lightThemeColors = MovioColors(
    brand = Brand(
        primary = Color(0xFF663EF6),
        onPrimary = Color(0xFFE6DFFF),
        primaryContainer = Color(0xFFD3CBF1),
        onPrimaryContainer = Color(0xFF292244)
    ),
    surfaces = Surfaces(
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF221D36),
        surfaceContainer = Color(0xFFFAF9FF),
        onSurfaceContainer = Color(0xFF7D7777),
        surfaceVariant = Color(0xFFE7E7EE),
        onSurfaceVariant = Color(0xFF929292),
        outline = Color(0xFFF9F8FA),
        outlineVariant = Color(0xFFF3F3F3),
        onSurfaceAt1 = Color(0xDEACABAC),
        onSurfaceAt2 = Color(0x61ACABAC),
        onSurfaceAt3 = Color(0x1FACABAC),
        onSurfaceAt4 = Color(0x66919191)
    ),
    system = System(
        error = Color(0xFFFEF4F2),
        onError = Color(0xFFB8311D),
        errorContainer = Color(0xFFEB5A44),
        onErrorContainer = Color(0xFFA53929),
        warning = Color(0xFFDBAF15),
        onWarning = Color(0xFFB28B00),
        onWarningContainer = Color(0xFF9F893A),
        success = Color(0xFF2C922A),
        onSuccess = Color(0xFFF6FFF6),
        successContainer = Color(0xFFE7FFE6),
        onSuccessContainer = Color(0xFF136912),
        dropShadow = Color(0x0F000000),
        defaultImageBackground = Color(0xFFA1AEC3)
    ),
    gradients = Gradients(
        iconGradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF663EF6),
                Color(0xFFB7A4FB)
            )
        )
    )
)