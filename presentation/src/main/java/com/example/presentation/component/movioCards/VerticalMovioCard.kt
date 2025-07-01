package com.example.presentation.component.movioCards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioText

@Composable
fun VerticalMovioCard(
    description: String, movieImage: Painter,
    modifier: Modifier = Modifier,
    rate: String,
    width: Dp,
    height: Dp,
    paddingvalue: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            Row(
                modifier = Modifier
                    .zIndex(1f)
                    .width(width)
                    .padding(paddingvalue),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                RatreIcon(rate = rate, tint = AppTheme.colors.systemColors.warning)
            }
            BasicImageCard(
                image = movieImage,
                height = height,
                width = width,
                modifier = Modifier
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
    VerticalMovioCard(
        "Spider-Man: Homecoming",
        movieImage = painterResource(com.example.designsystem.R.drawable.empty),
        rate = "3.0",
        width = 180.dp,
        height = 150.dp,
        paddingvalue = 8.dp
    )
}