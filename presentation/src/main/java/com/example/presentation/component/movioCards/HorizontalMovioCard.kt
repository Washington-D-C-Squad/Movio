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
    moviTitle: String,
    moviRate: String,
    moviCategory: String,
    moviImage: Painter,
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier,
    onClick:()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BasicImageCard(image = moviImage, height = height, width = width
        )
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            MovioText(
                text = moviTitle,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.title.medium14,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            RatreIcon(rate = moviRate, tint = AppTheme.colors.systemColors.warning)
            MovioCatergory(moviCategory,AppTheme.colors.surfaceColor.onSurface_3)
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
            .padding(vertical = 4.5.dp, horizontal = 12.dp),
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
    HorizontalMovioCard(
        moviTitle = "Spider-Man: Homecoming",
        moviImage = painterResource(com.example.designsystem.R.drawable.empty),
        moviRate = "3.0",
        width = 180.dp,
        height = 150.dp,
        moviCategory = "Action",
        onClick = {},
    )
}