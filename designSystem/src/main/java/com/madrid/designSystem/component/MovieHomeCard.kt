package com.madrid.designSystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.madrid.designSystem.R
import com.madrid.designSystem.theme.Theme


@Composable
fun MovieHomeCard(
    name: String,
    movieId: Int,
    imageRes: String = "https://example.com/poster.jpg",
    genres: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        Image(
            painter = painterResource(R.drawable.film_photo_sample), // Replace with actual image loading
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
                .height(200.dp)
        )
        MovioIcon(
            painter = painterResource(R.drawable.bold_video_circle),
            contentDescription = "",
            tint = Theme.color.brand.onPrimary,
            modifier = Modifier
                .align(Alignment.Center)
                .size(48.dp)
                .clickable {
                    onClick()
                }
        )
        Box(
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(51.dp)

        ) {
            Box(
                Modifier
                    .matchParentSize()
                    .blur(100.dp)
                    .drawBehind {
                        drawRect(
                            color = Color(0x1F000000),
                            topLeft = Offset(0f, 12f),
                            size = size,

                            )
                    }
                    .background(Color(0x66000000))
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                MovioText(
                    name,
                    color = Theme.color.brand.onPrimary,
                    textStyle = Theme.textStyle.label.mediumMedium12,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    genres.forEach {
                        MovioText(
                            text = it,
                            color = Theme.color.surfaces.onSurfaceContainer,
                            textStyle = Theme.textStyle.body.smallRegular10,
                            modifier = Modifier
                                .border(
                                    width = 0.5.dp,
                                    Theme.color.surfaces.onSurfaceAt3,
                                    RoundedCornerShape(24.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                }
            }
        }
    }
}