package com.madrid.presentation.screens.similarMovies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.composables.movioCards.MovioVerticalCard
import com.madrid.domain.entity.Movie

@Composable
fun SimilarMoviesScreen(
    onBackClick: () -> Unit = {},
    onMovieClick: (Movie) -> Unit = {},
    movies: List<Movie> = emptyList(),
    modifier: Modifier = Modifier
) {
    var selectedMovieId by remember { mutableStateOf<Int?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surfaceColor.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                MovioIcon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "",
                    tint = AppTheme.colors.surfaceColor.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            MovioText(
                text = stringResource(id = com.madrid.presentation.R.string.selected_similar_movie),
                textStyle = AppTheme.textStyle.headLine.largeBold18,
                color = AppTheme.colors.surfaceColor.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(movies) { movie ->
                MovioVerticalCard(
                    description = movie.title,
                    movieImage = movie.imageUrl,
                    rate = movie.rate.toString(),
                    width = 101.33.dp,
                    height = 136.dp,
                    onClick = {
                        selectedMovieId = movie.id
                        onMovieClick(movie)
                    },
                    modifier = Modifier,
                    paddingvalue = 0.dp
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SimilarMoviesScreenPreview() {
    SimilarMoviesScreen(
        onBackClick = {},
        onMovieClick = {},
        movies = emptyList()
    )
} 