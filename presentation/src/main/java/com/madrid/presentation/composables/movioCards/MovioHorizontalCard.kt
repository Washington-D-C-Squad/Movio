package com.madrid.presentation.composables.movioCards


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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun MovioHorizontalCard(
    movieTitle: String,
    movieRate: String,
    movieCategory: String,
    movieImageUrl: String,
    height: Dp,
    width: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.small)
            .clip(RoundedCornerShape(AppTheme.radius.small))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
    ) {
        BasicImageCard(
            imageUrl = movieImageUrl,
            modifier = Modifier.fillMaxWidth().height(height),
            radius = AppTheme.radius.small
        )
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical = AppTheme.spacing.extraSmall),
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
            MovioCatergory(movieCategory, AppTheme.colors.surfaceColor.onSurface_3)
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
private fun HorizontalCardPreview() {
    AppTheme {
        MovioHorizontalCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            movieRate = "3.0",
            width = 180.dp,
            height = 150.dp,
            movieCategory = "Action",
            onClick = {},
        )
    }
}