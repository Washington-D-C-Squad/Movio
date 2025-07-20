package com.madrid.presentation.composables.movioCards


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.madrid.design_system.AppTheme
import com.madrid.design_system.component.MovioText
import com.madrid.presentation.R

@Composable
fun MovioSeasonCard(
    movieTitle: String,
    movieRate: String,
    totalNumberOfEpisodes: String,
    movieImage: String,
    yearOfPublish:String,
    timeOfPublish:String,
    currentSeason:String,
    height: Dp,
    width: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        BasicImageCard(
            imageUrl = movieImage,
            modifier = Modifier.fillMaxWidth().height(height),
            radius = 8.dp
        )
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioText(
                    text = stringResource(
                        R.string.season,
                        currentSeason
                    ),
                    color = AppTheme.colors.surfaceColor.onSurface,
                    textStyle = AppTheme.textStyle.title.medium14,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    )
                RateIcon(rate = movieRate, tint = AppTheme.colors.systemColors.warning)
            }
            Column  {
                YearAndTotalEpisodes(
                    year=yearOfPublish,
                    totalEpisodes = totalNumberOfEpisodes
                )
                MovieDetails(
                    movieTitle = movieTitle,
                    currentSeason = currentSeason,
                    yearOfPublish = yearOfPublish,
                    timeOfPublish=timeOfPublish
                )
            }
        }
    }
}


@Composable
private fun YearAndTotalEpisodes(
    year: String,
    totalEpisodes: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = year,
            textStyle = AppTheme.textStyle.label.smallRegular12,
            color = AppTheme.colors.surfaceColor.onSurfaceContainer
        )
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
                .background(AppTheme.colors.surfaceColor.onSurfaceContainer)
        )
        MovioText(
            text = stringResource(
                R.string.episodes,
                totalEpisodes
            ),
            textStyle = AppTheme.textStyle.label.smallRegular12,
            color = AppTheme.colors.surfaceColor.onSurfaceContainer
        )
    }
}

@Composable
private fun MovieDetails(
    movieTitle: String,
    currentSeason: String,
    yearOfPublish: String,
    timeOfPublish: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = stringResource(R.string.season, currentSeason),
                textStyle = AppTheme.textStyle.label.smallRegular12,
                color = AppTheme.colors.surfaceColor.onSurfaceContainer
            )
            MovioText(
                text = stringResource(R.string.of, movieTitle),
                textStyle = AppTheme.textStyle.label.smallRegular12,
                color = AppTheme.colors.surfaceColor.onSurfaceContainer,
                maxLines = 1
            )
        }
        MovioText(
            text = "$timeOfPublish $yearOfPublish",
            textStyle = AppTheme.textStyle.label.smallRegular12,
            color = AppTheme.colors.surfaceColor.onSurfaceContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SeasonCardPreview() {
    AppTheme{
        MovioSeasonCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImage = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            movieRate =" 3.0",
            width = 100.dp,
            height = 74.dp,
            totalNumberOfEpisodes = "1",
            onClick = {},
            yearOfPublish ="2004",
            currentSeason = "7",
            timeOfPublish = "october 4 , 2002"
        )
    }
}
