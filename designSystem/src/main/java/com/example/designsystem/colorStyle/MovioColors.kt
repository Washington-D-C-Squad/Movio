import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class MovioColors(
    val brandColors: BrandColors,
    val surfaceColor: SurfaceColor,
    val systemColors: SystemColors,
)

data class BrandColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
)

data class SurfaceColor(
    val surface: Color,
    val onSurface: Color,
    val surfaceContainer: Color,
    val onSurfaceContainer: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val onSurface_1: Color,
    val onSurface_2: Color,
    val onSurface_3: Color,
    val onSurface_4: Color,
)

data class SystemColors(
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val onWarningContainer: Color,
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color
)