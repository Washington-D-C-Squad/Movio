import androidx.compose.ui.graphics.Color
import com.example.designsystem.colorStyle.BrandColors
import com.example.designsystem.colorStyle.SurfaceColor
import com.example.designsystem.colorStyle.SystemColors

val dark = MovioColors(
    brandColors = BrandColors(
        primary = Color(0xFF724CF8),
        onPrimary = Color(0xFFEBE6FE),
        primaryContainer = Color(0xFF181D32),
        onPrimaryContainer = Color(0xFFEFEFF1),
    ),
    surfaceColor = SurfaceColor(
        surface = Color(0xFF080d24),
        onSurface = Color(0xFFF0F5FF),
        surfaceContainer = Color(0xFF1A162F),
        onSurfaceContainer = Color(0xFFAEB3CC),
        surfaceVariant = Color(0xFF232940),
        onSurfaceVariant = Color(0xFF999DB3),
        outline = Color(0xFF434246),
        outlineVariant = Color(0xFF2F2E34),
        onSurface_1 = Color(0xDEFFFFFF),
        onSurface_2 = Color(0x61FFFFFF),
        onSurface_3 = Color(0x1FFFFFFF),
        onSurface_4 = Color(0x991A162F),
    ),
    systemColors = SystemColors(
        error = Color(0xFF2A1010),
        onError = Color(0xFFFFDEDF),
        errorContainer = Color(0xFFEE7277),
        onErrorContainer = Color(0xFFE53935),
        warning = Color(0xFFDD6D2D),
        onWarning = Color(0xFFFFFEF9),
        onWarningContainer = Color(0xFFC6BFA2),
        success = Color(0xFF2C922A),
        onSuccess = Color(0xFFF6FFF6),
        successContainer = Color(0xFFE7FFE6),
        onSuccessContainer = Color(0xFF136912)
    ),
)