package com.example.presentation.component.movioCards


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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.R
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.component.MovioText

@Composable
fun EpisodesCard(
    movieTitle: String,
    movieRate: Double,
    currentMovieEpisode: Int,
    movieTime: String,
    movieImage: Painter,
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
        EpisodeMovieImage(movieImage = movieImage, height = height, width = width)
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
    movieImage: Painter,
    height: Dp, width: Dp
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        BasicImageCard(image = movieImage, height = height, width = width)
        MovioIcon(
            contentDescription = stringResource(
                com.example.presentation.R.string.bold_video_circle
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
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioIcon(
                painter = painterResource(R.drawable.dot),
                contentDescription = stringResource(com.example.presentation.R.string.dot),
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
    numberOfEpisodes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(end = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovioText(
            text = stringResource(
                id = com.example.presentation.R.string.current_episode,
                String.format("%02d", numberOfEpisodes)
            ),
            textStyle = AppTheme.textStyle.label.smallRegular12,
            color = AppTheme.colors.surfaceColor.onSurfaceContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EpisodesCardPreview() {
    AppTheme{
        EpisodesCard(
            movieTitle = "Spider-Man: Homecoming",
            movieImage = painterResource(com.example.designsystem.R.drawable.film_photo_sample),
            movieRate = 3.0,
            width = 100.dp,
            height = 74.dp,
            currentMovieEpisode = 1,
            movieTime = "44 m",
            onClick = {}
        )
    }

}
