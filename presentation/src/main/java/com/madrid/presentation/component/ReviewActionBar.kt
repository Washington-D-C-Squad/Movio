package com.madrid.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
@Composable
fun ReviewTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    centerText: String? = null,
    onShareClick: (() -> Unit)? = null,
    onFavoriteClick: (() -> Unit)? = null,
    initiallyFavorite: Boolean = false
) {
    var isFavorite by rememberSaveable { mutableStateOf(initiallyFavorite) }
    val favoriteColor by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else AppTheme.colors.surfaceColor.onSurface,
        label = "FavoriteColor"
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = AppTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            iconRes = R.drawable.arrow_left,
            contentDescription = "Back",
            tint = AppTheme.colors.surfaceColor.onSurface,
            onClick = onBackClick
        )
        centerText?.let {
            MovioText(
                text = it,
                textStyle = AppTheme.textStyle.label.medium14,
                color = AppTheme.colors.surfaceColor.onSurface
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)) {
            onShareClick?.let {
                IconButton(
                    iconRes = R.drawable.share_arrow,
                    contentDescription = "Share",
                    tint = AppTheme.colors.surfaceColor.onSurface,
                    onClick = it
                )
            }
            onFavoriteClick?.let {
                IconButton(
                    iconRes = if (isFavorite) R.drawable.bold_heart else R.drawable.outline_heart,
                    contentDescription = "Favorite",
                    tint = favoriteColor,
                    onClick = {
                        isFavorite = !isFavorite
                        it()
                    }
                )
            }
        }
    }
}
@Composable
private fun IconButton(
    iconRes: Int,
    contentDescription: String?,
    tint: Color,
    onClick: () -> Unit
) {
    MovioIcon(
        painter = painterResource(iconRes),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(24.dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        tint = tint
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReviewActionBarPreview() {
    ReviewTopBar(
        onBackClick = {  },
        onShareClick = {},
        onFavoriteClick = {}

    )
}
