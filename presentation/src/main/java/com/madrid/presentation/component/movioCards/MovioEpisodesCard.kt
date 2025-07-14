package com.madrid.presentation.component.movioCards


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.R
import com.madrid.presentation.R.string
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@Composable
fun MovioEpisodesCard(
    movieTitle: String,
    movieRate: String,
    currentMovieEpisode: String,
    movieTime: String,
    movieImageUrl: String,
    height: Dp,
    width: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.small)
            .clip(RoundedCornerShape(AppTheme.radius.small))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
        verticalAlignment = Alignment.Top
    ) {
        EpisodeMovieImage(movieImageUrl = movieImageUrl, height = height, width = width)
        Column(
            modifier = modifier
                .height(height)
                .padding(vertical = AppTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovioText(
                    modifier = Modifier.weight(1f),
                    text = movieTitle, color = AppTheme.colors.surfaceColor.onSurface,
                    textStyle = AppTheme.textStyle.title.medium14,
                    maxLines = 1,
                )
                RateIcon(rate = movieRate, tint = AppTheme.colors.systemColors.warning)
            }
            Row {
                NumberOfEpisodes(numberOfEpisodes = currentMovieEpisode)
                TimeSection(movieTime)
            }
        }
    }
}

@Composable
private fun EpisodeMovieImage(
    movieImageUrl: String,
    height: Dp, width: Dp
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        BasicImageCard(
            imageUrl = movieImageUrl,
            height = height, width = width,
            radius = AppTheme.radius.small,
        )
        MovioIcon(
            contentDescription = stringResource(
                string.bold_video_circle
            ),
            tint = AppTheme.colors.surfaceColor.onSurface_1,
            painter = painterResource(R.drawable.bold_video_circle),
            modifier = Modifier.size(34.dp)
        )
    }
}

@Composable
private fun TimeSection(
    movieTime: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioIcon(
                painter = painterResource(R.drawable.dot),
                contentDescription = stringResource(string.dot),
                modifier = Modifier.size(12.dp),
                tint = AppTheme.colors.surfaceColor.onSurfaceContainer
            )
            MovioText(
                text = movieTime,
                textStyle = AppTheme.textStyle.label.smallRegular12,
                color = AppTheme.colors.surfaceColor.onSurfaceContainer
            )
        }

    }
}

@Composable
private fun NumberOfEpisodes(
    numberOfEpisodes: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(end = AppTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = stringResource(string.current_episode,
            numberOfEpisodes),
            textStyle = AppTheme.textStyle.label.smallRegular12,
            color = AppTheme.colors.surfaceColor.onSurfaceContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EpisodesCardPreview() {
    AppTheme {
        MovioEpisodesCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImageUrl = "https://image.tmdb.org/t/p/w500/5xKGk6q5g7mVmg7k7U1RrLSHwz6.jpg",
            movieRate = "3.0",
            width = 100.dp,
            height = 74.dp,
            currentMovieEpisode = "1",
            movieTime = "44 m",
            onClick = {}
        )
    }
}
