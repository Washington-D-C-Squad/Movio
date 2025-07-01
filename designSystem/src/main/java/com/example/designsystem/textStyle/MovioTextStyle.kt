import com.example.designsystem.textStyle.BodyTextStyle
import com.example.designsystem.textStyle.DisplayTextStyle
import com.example.designsystem.textStyle.HeadlineTextStyle
import com.example.designsystem.textStyle.LabelTextStyle
import com.example.designsystem.textStyle.TitleTextStyle

data class MovioTextStyle(
    val display: DisplayTextStyle,
    val headLine: HeadlineTextStyle,
    val title: TitleTextStyle,
    val body: BodyTextStyle,
    val label: LabelTextStyle,
)