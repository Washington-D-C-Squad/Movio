package com.example.presentation.component.movioCards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText

@Composable
fun RateIcon(
    rate: String,
    tint:Color,
    modifier: Modifier = Modifier,
    icon: Painter=painterResource(com.example.designsystem.R.drawable.bold_star),
    ) {
    Box(
        modifier = modifier
            .height(AppTheme.spacing.medium)
            .padding(end = AppTheme.spacing.small)
        ,contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioIcon(
                painter = icon,
                contentDescription = stringResource(
                    com.example.presentation.R.string.stars_icon_rate
                ),
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
            MovioText(
                text = rate,
                color = AppTheme.colors.systemColors.onWarning,
                textStyle = AppTheme.textStyle.label.smallRegular12,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatreIconpreview() {
    RateIcon(
        icon = painterResource(com.example.designsystem.R.drawable.bold_star),
        rate = "5.0",
        tint = AppTheme.colors.systemColors.warning,
        modifier = Modifier
            .height(16.dp)
            .width(38.dp)
    )
}