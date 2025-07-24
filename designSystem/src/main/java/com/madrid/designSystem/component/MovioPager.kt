package com.madrid.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
fun MovioPager(
    movies: List<Int>,
    onClickItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = movies.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 80.dp),
            modifier = Modifier
                .height(250.dp)
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page)
            val absOffset = pageOffset.absoluteValue

            val rotationZ = lerp(
                start = 45f,
                stop = 0f,
                fraction = 1f - absOffset.coerceIn(0f, 1f)
            ) * if (pageOffset < 0) 1f else -1f

            val alpha = lerp(
                start = 0.4f,
                stop = 1f,
                fraction = 1f - absOffset.coerceIn(0f, 1f)
            )

            Box(
                modifier = Modifier
                    .graphicsLayer {
//                        this.rotationY = rotationY
                        this.rotationZ = rotationZ
                        this.alpha = alpha
                        cameraDistance = 12 * density
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Transparent)
                    .size(width = 200.dp, height = 260.dp)
            ) {
                MovieHomeCard(
                    movieId = movies[page],
                    name = "Movie $page",
                    genres = listOf("Action", "Adventure"),
                    onClick = {
                        onClickItem()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Custom indicator
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(movies.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (isSelected) 20.dp else 10.dp, 10.dp)
                        .clip(if (isSelected) RoundedCornerShape(50) else CircleShape)
                        .background(if (isSelected) Color.White else Color.Gray)
                )
            }
        }
    }

}


@Preview
@Composable
fun MovioSliderPreview() {
    MovioPager(
        movies = listOf(1, 2, 3, 4, 5),
        onClickItem = {}
    )
}