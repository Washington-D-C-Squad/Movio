import androidx.compose.ui.text.TextStyle

data class MovioTextStyle(
    val display: DisplayTextStyle,
    val headLine: HeadlineTextStyle,
    val title: TitleTextStyle,
    val body: BodyTextStyle,
    val label: LabelTextStyle,
)

data class DisplayTextStyle(
    val largeBold24: TextStyle,
    val largeBold20: TextStyle,
    val mediumBold24: TextStyle,
    val largeBold18: TextStyle,
)

data class HeadlineTextStyle(
    val largeBold18: TextStyle,
    val medium18: TextStyle,
    val largeBold16: TextStyle,
    val medium16: TextStyle,
)

data class TitleTextStyle(
    val largeBold16: TextStyle,
    val medium16: TextStyle,
    val largeBold14: TextStyle,
    val medium14: TextStyle,
)

data class BodyTextStyle(
    val smallRegular16: TextStyle,
    val medium14: TextStyle,
    val medium12: TextStyle,
    val smallRegular10: TextStyle,
)

data class LabelTextStyle(
    val largeMedium16: TextStyle,
    val medium14: TextStyle,
    val smallRegular14: TextStyle,
    val medium12: TextStyle,
    val smallRegular12: TextStyle,
)
