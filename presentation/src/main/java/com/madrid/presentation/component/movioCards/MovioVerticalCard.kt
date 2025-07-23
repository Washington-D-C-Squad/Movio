package com.madrid.presentation.component.movioCards


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme

@Composable
fun MovioVerticalCard(
    description: String,
    movieImage: String,
    rate: String,
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValue: Dp = 8.dp,
    ) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Row(
                modifier = Modifier
                    .zIndex(1f)
                    .width(width)
                    .padding(top = paddingValue , end = paddingValue),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                RateIcon(rate = (rate.take(3).toFloat()/2).toString().take(3))
            }
            BasicImageCard(
                imageUrl = movieImage,
                radius = 8.dp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .width(width)
                    .height(height)
                    .clip(RoundedCornerShape(8.dp))
            )

        }

        MovioText(
            text = description,
            textStyle = Theme.textStyle.title.mediumMedium14,
            color = Theme.color.surfaces.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(width)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VerticalCardPreview() {
    MovioTheme {
        MovioVerticalCard(
            description = "Spider-Man",
            movieImage = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            width = 200.dp,
            height = 150.dp,
            paddingValue = 8.dp,
            onClick = {},
            rate = "4.0",
        )
    }
}