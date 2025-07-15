package com.madrid.presentation.composables.movioCards


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText

@Composable
fun MovioVerticalCard(
    description: String,
    movieImage: String,
    rate: String,
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingvalue: Dp = 8.dp,
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
            BasicImageCard(
                imageUrl = movieImage,
                height = height,
                width = width,
                radius = AppTheme.radius.small,
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
private fun VerticalCardPreview() {
    AppTheme {
        MovioVerticalCard(
            description = "Spider-Man: Homecoming",
            movieImage = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            width = 180.dp,
            height = 150.dp,
            paddingvalue = 8.dp,
            onClick = {},
            rate = "4.0",
        )
    }
}