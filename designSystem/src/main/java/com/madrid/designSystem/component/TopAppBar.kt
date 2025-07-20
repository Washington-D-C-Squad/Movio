package com.madrid.designSystem.component

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
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R

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
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        firstIcon?.let { iconRes ->
            MovioIcon(
                painter = painterResource(id = iconRes),
                contentDescription = "arrow_left",
                tint = Theme.color.surfaces.onSurface
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (text != null) {
                MovioText(
                    text = text,
                    textStyle = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface
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
                    tint = Theme.color.surfaces.onSurface
                )
            }

            thirdIcon?.let { iconRes ->
                MovioIcon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "outline_heart",
                    tint = Theme.color.surfaces.onSurface
                )
            }
        }
    }
}