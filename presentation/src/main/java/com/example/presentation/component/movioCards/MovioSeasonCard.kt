package com.example.presentation.component.movioCards


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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioText
import com.example.presentation.R

@Composable
fun MovioSeasonCard(
    movieTitle: String,
    movieRate: Double,
    totalNumberOfEpisodes: Int,
    movieImage: Painter,
    yearOfPublish:Int,
    timeOfPublish:String,
    currentSeason:Int,
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
             .clip(RoundedCornerShape(AppTheme.radius.small))
             .clickable { onClick() }
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        BasicImageCard(image = movieImage, height = height, width = width)
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
    year: Int,
    totalEpisodes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = year.toString(),
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
    currentSeason: Int,
    yearOfPublish: Int,
    timeOfPublish: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(end = AppTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.extraSmall)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
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
fun seasonCardPreview() {
    AppTheme{
        MovioSeasonCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImage = painterResource(com.example.designsystem.R.drawable.film_photo_sample),
            movieRate = 3.0,
            width = 100.dp,
            height = 74.dp,
            totalNumberOfEpisodes = 1,
            onClick = {},
            yearOfPublish = 2004,
            currentSeason = 7,
            timeOfPublish = "october 4 , 2002"
        )
    }
}
