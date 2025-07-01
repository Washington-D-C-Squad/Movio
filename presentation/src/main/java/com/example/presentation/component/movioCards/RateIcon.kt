package com.example.presentation.component.movioCards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText

@Composable
fun RatreIcon(
    icon: Painter=painterResource(com.example.designsystem.R.drawable.bold_star),
    rate: String,
    modifier: Modifier = Modifier,
    tint:Color
) {
    Box(
        modifier = modifier
            .height(16.dp)
        ,contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioIcon(
                painter = icon,
                contentDescription = "stars icon rate",
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
            MovioText(
                text = rate, color = Color(0xFFFFFEF9),
                textStyle = AppTheme.textStyle.label.smallRegular12,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatreIconpreview() {
    RatreIcon(
        icon = painterResource(com.example.designsystem.R.drawable.bold_star),
        rate = "5.0",
        tint = AppTheme.colors.systemColors.warning,
        modifier = Modifier
            .height(16.dp)
            .width(38.dp)
    )
}