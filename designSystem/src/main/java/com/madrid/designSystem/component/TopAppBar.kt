package com.madrid.designSystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designSystem.R
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme

@Composable
fun TopAppBar(
    text: String?,
    modifier: Modifier = Modifier,
    firstIcon: Int? = R.drawable.arrow_left,
    secondIcon: Int? = R.drawable.share_arrow,
    thirdIcon: Int? = R.drawable.outline_heart,
    initiallyFavorite: Boolean = false
) {
    var isFavorite by rememberSaveable { mutableStateOf(initiallyFavorite) }

    val favoriteColor by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else Theme.color.surfaces.onSurface,
        label = "FavoriteColor"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(1f)
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
            text?.let {
                MovioText(
                    text = it,
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
            thirdIcon?.let {
                MovioIcon(
                    painter = painterResource(id = if (isFavorite) R.drawable.bold_heart else R.drawable.outline_heart),
                    contentDescription = "favorite_heart",
                    tint = favoriteColor,
                    modifier = Modifier.clickable {
                        isFavorite = !isFavorite
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TopAppBarReview(modifier: Modifier = Modifier) {
    MovioTheme {
        TopAppBar(null)
    }
}