package com.madrid.presentation.composables.movioCards


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
import com.madrid.presentation.R.string
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText


@Composable
fun RateIcon(
    rate: String,
    tint: Color,
    modifier: Modifier = Modifier,
    icon: Painter = painterResource(R.drawable.bold_star),
) {
    Box(
        modifier = modifier
            .height(16.dp)
            .padding(end = 8.dp), contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioIcon(
                painter = icon,
                contentDescription = stringResource(
                   string.stars_icon_rate
                ),
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
            MovioText(
                text = rate,
                color = Theme.color.system.onWarning,
                textStyle = Theme.textStyle.label.smallRegular12,
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RateIconPreview() {
    RateIcon(
        icon = painterResource(R.drawable.bold_star),
        rate = "5.0",
        tint = Theme.color.system.warning,
        modifier = Modifier
            .height(16.dp)
            .width(38.dp)
    )
}