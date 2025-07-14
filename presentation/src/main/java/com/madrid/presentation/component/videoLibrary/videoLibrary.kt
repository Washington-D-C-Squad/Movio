package com.madrid.presentation.component.videoLibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designsystem.MovioTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon

@Composable
fun VideoLibrary(
    onClick: () -> Unit,
    videosNumber: Int
) {
    BoxWithConstraints {
        val isDark = isSystemInDarkTheme()
        val width = maxWidth * (158f / 360f)
        val height = maxHeight * (128f / 800f)
        Column(
            modifier = Modifier
                .padding(top = 80.dp)
                .size(width = width, height = height)
                .clip(RoundedCornerShape(MovioTheme.radius.small))
                .background(color = MovioTheme.colors.surfaceColor.surface)
                .clickable { onClick() }
        ) {
            Box(
                modifier = Modifier.weight(1f)
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(3f)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MovioTheme.colors.surfaceColor.surface.copy(alpha = 0.3f),
                                    MovioTheme.colors.surfaceColor.surface.copy(alpha = 0.8f)
                                ),
                            )
                        )
                )
                ShowGridForVideoLibraryBackground()

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(3f)
                        .shadow(
                            elevation = 33.dp,
                            clip = false,
                            shape = CircleShape,
                            spotColor = Color(0x3D663EF6)
                        )
                        .padding(10.dp)
                ) {
                    MovioIcon(
                        painter = painterResource(R.drawable.library_main_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }

                MovioIcon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .zIndex(3f),
                    painter = painterResource(R.drawable.star_img),
                    contentDescription = "",
                    tint = if (isDark) Color.White else Color.Black
                )
                MovioIcon(
                    modifier = Modifier
                        .zIndex(3f)
                        .align(Alignment.BottomStart)
                        .padding(bottom = 21.dp, start = 6.dp),
                    painter = painterResource(R.drawable.star_img_four),
                    contentDescription = "",
                    tint = if (isDark) Color.White else Color.Black
                )
            }
            BottomRowForVideoLibrary(videosNumber)
        }
    }
}

@Composable
fun ShowGridForVideoLibraryBackground() {
    GridWithFullBorders(
        Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxSize()
            .zIndex(2f)
            .alpha(0.2f),
        highlightedCells = setOf(
            Pair(2, 2),
            Pair(2, 4),
            Pair(4, 5)
        )
    )
    GridWithFullBorders(
        Modifier
            .fillMaxSize()
            .alpha(0.2f)
            .zIndex(2f)
            .offset(x = (105).dp, y = 40.dp)
            .padding(horizontal = MovioTheme.spacing.large),
        highlightedCells = setOf(
            Pair(4, 3),
        )
    )
}

