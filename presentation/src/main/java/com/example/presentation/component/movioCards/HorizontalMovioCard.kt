package com.example.presentation.component.movioCards


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioText

@Composable
fun HorizontalMovioCard(
    movieTitle: String,
    movieRate: Double,
    movieCategory: String,
    movieImage: Painter,
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier,
    onClick:()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.small)
            .clip(RoundedCornerShape(AppTheme.radius.small))
            .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
    ) {
        BasicImageCard(image = movieImage, height = height, width = width
        )
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical =AppTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            MovioText(
                text = movieTitle,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.title.medium14,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            RateIcon(rate = movieRate, tint = AppTheme.colors.systemColors.warning)
            MovioCatergory(movieCategory,AppTheme.colors.surfaceColor.onSurface_3)
        }
    }
}

@Composable
private fun MovioCatergory(
    moviCategory: String,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(60.dp))
            .padding(vertical = AppTheme.spacing.extraSmall, horizontal = AppTheme.spacing.medium),
        horizontalArrangement = Arrangement.Center
    ) {
        MovioText(
            text = moviCategory, color = AppTheme.colors.surfaceColor.onSurfaceVariant,
            textStyle = AppTheme.textStyle.label.smallRegular12,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HorizontalCardPreview() {
    AppTheme{
        HorizontalMovioCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImage = painterResource(com.example.designsystem.R.drawable.film_photo_sample),
            movieRate = 3.0,
            width = 180.dp,
            height = 150.dp,
            movieCategory = "Action",
            onClick = {},
        )
    }
}