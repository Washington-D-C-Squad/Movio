package com.madrid.presentation.composables.movioCards

import android.R.string
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@Composable
fun MovioEpisodesCard(
    movieTitle: String,
    movieRate: String,
    currentMovieEpisode: String,
    movieTime: String,
    movieImageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(74.dp)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .height(74.dp)
        ) {
            BasicImageCard(
                imageUrl = movieImageUrl,
                modifier = Modifier
                    .height(74.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(AppTheme.radius.small)),
                radius = AppTheme.radius.small,
            )
            MovioIcon(
                contentDescription = "video circle",
                tint = Color.White,
                painter = painterResource(R.drawable.bold_video_circle),
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )
        }
        FrameEpisodeCard(
            modifier = modifier
                .weight(1f),
            movieTitle = movieTitle,
            movieRate = movieRate,
            currentMovieEpisode = currentMovieEpisode,
            movieTime = movieTime,
        )
    }
}

@Composable
private fun FrameEpisodeCard(
    movieTitle: String,
    movieRate: String,
    currentMovieEpisode: String,
    movieTime: String,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(top = 15.dp, bottom = 15.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            MovioText(
                modifier = Modifier.weight(1f),
                text = movieTitle,
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.title.medium14,
            )
            RateIcon(
                rate = movieRate,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                modifier = Modifier.padding(end = 4.dp),
                text = currentMovieEpisode,
                color = AppTheme.colors.surfaceColor.onSurfaceContainer,
                textStyle = AppTheme.textStyle.title.medium14,
            )
            MovioIcon(
                painter = painterResource(R.drawable.dot),
                contentDescription = "dot icon",
                modifier = Modifier.size(12.dp),
                tint = AppTheme.colors.surfaceColor.onSurfaceContainer
            )
            MovioText(
                text = movieTime,
                color = AppTheme.colors.surfaceColor.onSurfaceContainer,
                textStyle = AppTheme.textStyle.title.medium14,
            )
        }
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
            currentMovieEpisode = "Episode 01",
            movieTime = "44 m",
            onClick = {}
        )
    }
}