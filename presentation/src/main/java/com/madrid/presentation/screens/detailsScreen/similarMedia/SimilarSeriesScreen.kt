package com.madrid.presentation.screens.detailsScreen.similarMedia

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.detectImageContent.FilteredImage
import com.madrid.presentation.R

data class SimilarSeries(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val rating: Double,
)

@Composable
fun SimilarSeriesSection(
    similarSeries: List<SimilarSeries>,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {},
    onSeriesClick: (SimilarSeries) -> Unit = {}
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "Similar Series",
                color = Theme.color.surfaces.onSurface,
                textStyle = Theme.textStyle.headline.mediumMedium18
            )

            MovioText(
                text = stringResource(id = R.string.see_all),
                color = Theme.color.surfaces.onSurfaceVariant,
                textStyle = Theme.textStyle.label.smallRegular14,
                modifier = Modifier.clickable(onClick = onSeeAllClick)
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(similarSeries) { series ->
                SeriesCard(
                    series = series,
                    onClick = { onSeriesClick(series) }
                )
            }
        }
    }
}

@Composable
private fun SeriesCard(
    series: SimilarSeries,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(124.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {
            FilteredImage(
                imageUrl = series.imageUrl,
                contentDescription = series.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Theme.color.surfaces.surfaceContainer.copy(alpha = 0.7f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .align(Alignment.TopStart)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MovioIcon(
                        painter = painterResource(id = com.madrid.designSystem.R.drawable.bold_star),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Theme.color.system.warning
                    )
                    MovioText(
                        text = series.rating.toString(),
                        color = Theme.color.surfaces.onSurface,
                        textStyle = Theme.textStyle.label.smallRegular12
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        MovioText(
            text = series.title,
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.label.smallRegular12,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SimilarMoviesSectionPreview() {
    val fakeMovies = listOf(
        SimilarSeries(
            id = 1,
            title = "Spider-Man: Into the Spider-Verse",
            imageUrl = "https://image.tmdb.org/t/p/w500/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
            rating = 4.8
        ),
        SimilarSeries(
            id = 2,
            title = "The Dark Knight",
            imageUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            rating = 5.0
        ),
        SimilarSeries(
            id = 3,
            title = "Grave of the Fireflies",
            imageUrl = "https://image.tmdb.org/t/p/w500/qG3RYlIVpTYclR9TYIsy8p7m7AT.jpg",
            rating = 4.7
        )
    )
    MovioTheme {
        Box(
            modifier = Modifier.background(Theme.color.surfaces.surfaceContainer)
        ) {
            SimilarSeriesSection(similarSeries = fakeMovies)
        }
    }
}