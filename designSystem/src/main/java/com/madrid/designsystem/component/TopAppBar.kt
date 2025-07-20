package com.madrid.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R

@Composable
fun TopAppBar(
    text: String?,
    modifier: Modifier = Modifier,
    firstIcon: Int? = R.drawable.arrow_left,
    secondIcon: Int? = R.drawable.share_arrow,
    thirdIcon: Int? = R.drawable.outline_heart
) {
    Row(
        modifier = modifier
            .fillMaxWidth().zIndex(1f)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        firstIcon?.let { iconRes ->
            MovioIcon(
                painter = painterResource(id = iconRes),
                contentDescription = "arrow_left",
                tint = AppTheme.colors.surfaceColor.onSurface
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = AppTheme.spacing.small),
            contentAlignment = Alignment.Center
        ) {
            if (text != null) {
                MovioText(
                    text = text,
                    textStyle = AppTheme.textStyle.headLine.largeBold18,
                    color = AppTheme.colors.surfaceColor.onSurface
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            secondIcon?.let { iconRes ->
                MovioIcon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "share_arrow",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
            }

            thirdIcon?.let { iconRes ->
                MovioIcon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "outline_heart",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
            }
        }
    }
}