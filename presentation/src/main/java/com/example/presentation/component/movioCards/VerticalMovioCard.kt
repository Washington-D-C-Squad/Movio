import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioText
import com.example.presentation.component.movioCards.BasicImageCard
import com.example.presentation.component.movioCards.RateIcon

@Composable
fun VerticalMovioCard(
    description: String,
    movieImage: String,
    rate: String,
    width: Dp,
    height: Dp,
    paddingvalue: Dp = 8.dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.radius.small))
            .clickable { onClick() }
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            Row(
                modifier = Modifier
                    .zIndex(1f)
                    .width(width)
                    .padding(vertical = paddingvalue),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                RateIcon(rate = rate, tint = AppTheme.colors.systemColors.warning)
            }
            AsyncImage(
                model = movieImage,
                contentDescription = description,
            )
            BasicImageCard(
                imageUrl = movieImage,
                height = height,
                width = width,
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .clip(RoundedCornerShape(AppTheme.radius.small))
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        MovioText(
            text = description,
            textStyle = AppTheme.textStyle.title.medium14,
            color = AppTheme.colors.surfaceColor.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(width)
                .wrapContentWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalCardPreview() {
    AppTheme {
        VerticalMovioCard(
            description = "Spider-Man: Homecoming",
//            movieImage = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
//            "Spider-Man: Homecoming",
            movieImage = painterResource(R.drawable.film_photo_sample).toString(),
            width = 180.dp,
            height = 150.dp,
            paddingvalue = 8.dp,
            onClick = {},
            rate = "4.0",
        )
    }
}
