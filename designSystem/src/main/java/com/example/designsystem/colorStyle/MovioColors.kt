import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.designsystem.colorStyle.BrandColors
import com.example.designsystem.colorStyle.SurfaceColor
import com.example.designsystem.colorStyle.SystemColors

@Immutable
data class MovioColors(
    val brandColors: BrandColors,
    val surfaceColor: SurfaceColor,
    val systemColors: SystemColors,
)