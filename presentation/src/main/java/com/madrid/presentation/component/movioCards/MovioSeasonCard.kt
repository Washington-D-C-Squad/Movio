package com.madrid.presentation.component.movioCards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme


@Composable
fun MovioSeasonCard(
    movieTitle: String,
    movieRate: String,
    totalNumberOfEpisodes: String,
    movieImage: String,
    yearOfPublish: String,
    timeOfPublish: String,
    currentSeason: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BasicImageCard(
            imageUrl = movieImage,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(76.dp)
                .height(100.dp),
            radius = 8.dp
        )
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .align(Alignment.CenterVertically),
        ) {
            Row {
                MovioText(
                    text = "Season $currentSeason",
                    color = Theme.color.surfaces.onSurface,
                    textStyle = Theme.textStyle.title.mediumMedium14,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                )
                RateIcon(
                    rate = movieRate,
                )
            }
            MovioText(
                modifier = Modifier.padding(top = 8.dp),
                text = "$yearOfPublish | $totalNumberOfEpisodes Episodes",
                color = Theme.color.surfaces.onSurfaceVariant,
                textStyle = Theme.textStyle.title.mediumMedium14,
                maxLines = 1,
            )
            MovioText(
                modifier = Modifier.padding(top = 10.dp),
                text = "Season $currentSeason $movieTitle $timeOfPublish.",
                color = Theme.color.surfaces.onSurfaceContainer,
                textStyle = Theme.textStyle.title.mediumMedium14,
                maxLines = 3,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SeasonCardPreview() {
    MovioTheme {
        MovioSeasonCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImage = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            movieRate = " 3.0",
            totalNumberOfEpisodes = "1",
            onClick = {},
            yearOfPublish = "2004",
            currentSeason = "7",
            timeOfPublish = "october 4 , 2002"
        )
    }
}