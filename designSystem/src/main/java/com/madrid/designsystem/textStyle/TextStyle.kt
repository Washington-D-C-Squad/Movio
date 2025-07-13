import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.madrid.designsystem.R


val inter = FontFamily(
    Font(R.font.inter, weight = FontWeight.Normal),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
)


val defaultTextStyle = MovioTextStyle(

    display = DisplayTextStyle(
        largeBold24 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        ),
        largeBold20 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        ),
        mediumBold24 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        ),
        largeBold18 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
    ),

    headLine = HeadlineTextStyle(
        largeBold18 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        ),
        medium18 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        ),
        largeBold16 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        ),
        medium16 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
    ),

    title = TitleTextStyle(
        largeBold16 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        ),
        medium16 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        largeBold14 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        ),
        medium14 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
    ),


    body = BodyTextStyle(
        smallRegular16 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        smallRegular10 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
        ),
        medium12 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
        medium14 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
    ),
    label = LabelTextStyle(
        largeMedium16 =TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        medium14 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
        smallRegular14 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        medium12 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
        smallRegular12 = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
    )
)
