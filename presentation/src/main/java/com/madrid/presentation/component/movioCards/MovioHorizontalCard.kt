package com.madrid.presentation.component.movioCards


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
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme

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
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BasicImageCard(
            imageUrl = movieImageUrl,
            modifier = Modifier.fillMaxWidth().height(height),
            radius = 8.dp
        )
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            MovioText(
                text = movieTitle,
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.title.mediumMedium14,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            RateIcon(rate = movieRate)
            MovioCatergory(movieCategory, Theme.color.surfaces.onSurfaceAt3)
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
            .padding(vertical = 4.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        MovioText(
            text = moviCategory, color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.label.smallRegular12,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HorizontalCardPreview() {
    MovioTheme {
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